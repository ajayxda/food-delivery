package com.aj.Munchio.service;

import com.aj.Munchio.dto.auth.LoginRequest;
import com.aj.Munchio.dto.auth.RegisterRequest;
import com.aj.Munchio.dto.user.UserResponse;
import com.aj.Munchio.entity.user.User;
import com.aj.Munchio.exception.GlobalExceptionHandler;
import com.aj.Munchio.mapper.UserMapper;
import com.aj.Munchio.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;


    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponse signup(RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

       User savedUser =  userRepository.save(user);
       return userMapper.mapToDTO(savedUser);
    }

    public User login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        User fetchedUser = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new RuntimeException("User not found"));
        return fetchedUser;
    }
}
