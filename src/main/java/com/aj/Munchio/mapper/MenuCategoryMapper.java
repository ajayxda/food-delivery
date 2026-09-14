package com.aj.Munchio.mapper;

import com.aj.Munchio.dto.menu.menucategory.MenuCategoryResponse;
import com.aj.Munchio.entity.restaurant.MenuCategory;
import org.springframework.stereotype.Component;

@Component
public class MenuCategoryMapper {
    public MenuCategoryResponse mapToDto(MenuCategory menuCategory) {
        MenuCategoryResponse response = new MenuCategoryResponse();
        response.setId(menuCategory.getId());
        response.setName(menuCategory.getName());
        response.setDisplayOrder(menuCategory.getDisplayOrder());
        response.setCreatedAt(menuCategory.getCreatedAt());
        response.setUpdatedAt(menuCategory.getUpdatedAt());
        response.setMenuId(menuCategory.getMenu().getId());
        return response;
    }
}
