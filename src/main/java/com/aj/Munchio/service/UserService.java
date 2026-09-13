package com.aj.Munchio.service;

import com.aj.Munchio.dto.user.UserUpdateRequest;
import com.aj.Munchio.entity.user.User;
import com.aj.Munchio.dto.user.UserRequest;
import com.aj.Munchio.dto.user.UserResponse;
import com.aj.Munchio.exception.ResourceNotFoundException;
import com.aj.Munchio.mapper.UserMapper;
import com.aj.Munchio.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public UserResponse saveUser(UserRequest userRequest) {
        User newUser = new User();
        newUser.setName(userRequest.getName());
        newUser.setEmail(userRequest.getEmail());
        newUser.setPhone(userRequest.getPhone());
        newUser.setRole(userRequest.getRole());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        User user = userRepository.save(newUser);

        return userMapper.mapToDTO(user);
    }

    @Transactional
    public UserResponse patchUser(UUID userId, UserUpdateRequest userRequest) {
        //1. Fetch the existing user else throw error if user does not exist
        User existingUser = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found with id: " + userId));

        //2. update the fields with the new data
        if(userRequest.getName() !=null) {
            existingUser.setName(userRequest.getName());
        }

        if(userRequest.getPhone() != null) {
            existingUser.setPhone(userRequest.getPhone());
        }
        if(userRequest.getEmail()!=null) {
            existingUser.setEmail(userRequest.getEmail());
        }

        //3. return the updated user
        //JPA will automatically save the changes to the database at the end of the method
        return userMapper.mapToDTO(existingUser);
    }

    @Transactional
    public UserResponse updateUser(UUID userId, UserRequest userRequest) {
        //1. Fetch the existing user else throw error if user does not exist
        User existingUser = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found with id: " + userId));

        //2. update the fields with the new data
        existingUser.setName(userRequest.getName());
        existingUser.setPhone(userRequest.getPhone());
        existingUser.setEmail(userRequest.getEmail());

        //3. return the updated user
        //JPA will automatically save the changes to the database at the end of the method
        return userMapper.mapToDTO(existingUser);
    }

    public List<UserResponse> getAllUsers() {

         List<User> users = userRepository.findAll();

         return users.stream().map(userMapper::mapToDTO).toList();
    }

    public UserResponse getUserById(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id: "+userId));
        return userMapper.mapToDTO(user);
    }

    @Transactional
    public void deleteUserById(UUID userId) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("user not found");
        }
        userRepository.deleteById(userId);
    }
}
