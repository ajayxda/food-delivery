package com.aj.Munchio.rest;

import com.aj.Munchio.dto.auth.LoginRequest;
import com.aj.Munchio.dto.auth.LoginResponse;
import com.aj.Munchio.dto.auth.RegisterRequest;
import com.aj.Munchio.dto.user.UserResponse;
import com.aj.Munchio.entity.user.User;
import com.aj.Munchio.service.AuthenticationService;
import com.aj.Munchio.service.JwtService;
import com.aj.Munchio.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;


    public AuthController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse userResponse = authenticationService.signup(request);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        User registeredUser = authenticationService.login(request);
        String jwtToken = jwtService.generateToken(registeredUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }
}
