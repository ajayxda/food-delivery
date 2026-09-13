package com.aj.Munchio.rest;

import com.aj.Munchio.dto.user.UserRequest;
import com.aj.Munchio.dto.user.UserResponse;
import com.aj.Munchio.dto.user.UserUpdateRequest;
import com.aj.Munchio.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID userId) {
        UserResponse user = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("")
    public ResponseEntity<UserResponse> saveNewUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse user = userService.saveUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID userId,@Valid @RequestBody UserRequest userRequest) {
        UserResponse user = userService.updateUser(userId, userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> updatePartialUser(@PathVariable UUID userId,@Valid @RequestBody UserUpdateRequest userRequest) {
        UserResponse user = userService.patchUser(userId, userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
}
