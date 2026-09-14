package com.aj.Munchio.mapper;

import com.aj.Munchio.dto.menu.menuitem.MenuItemResponse;
import com.aj.Munchio.entity.restaurant.MenuItem;
import org.springframework.stereotype.Component;

@Component
public class MenuItemMapper {
    public MenuItemResponse mapToDto(MenuItem menuItem) {
        MenuItemResponse response = new MenuItemResponse();
        response.setId(menuItem.getId());
        response.setName(menuItem.getName());
        response.setDisplayOrder(menuItem.getDisplayOrder());
        response.setCreatedAt(menuItem.getCreatedAt());
        response.setUpdatedAt(menuItem.getUpdatedAt());
        response.setAvailable(menuItem.isAvailable());
        response.setDescription(menuItem.getDescription());
        response.setImageUrl(menuItem.getImageUrl());
        response.setVeg(menuItem.isVeg());
        response.setCategoryId(menuItem.getCategory().getId());
        response.setPrice(menuItem.getPrice());
        return response;
    }
}
