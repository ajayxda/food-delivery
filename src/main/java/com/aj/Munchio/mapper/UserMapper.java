package com.aj.Munchio.mapper;

import com.aj.Munchio.dto.user.UserResponse;
import com.aj.Munchio.entity.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse mapToDTO(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }
}
