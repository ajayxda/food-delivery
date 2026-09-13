package com.aj.Munchio.mapper;

import com.aj.Munchio.dto.menu.MenuResponse;
import com.aj.Munchio.entity.restaurant.Menu;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {
    public MenuResponse mapToDto(Menu menu) {
        MenuResponse response = new MenuResponse();
        response.setId(menu.getId());
        response.setName(menu.getName());
        response.setCreatedAt(menu.getCreatedAt());
        response.setUpdatedAt(menu.getUpdatedAt());
        response.setRestaurantId(menu.getRestaurant().getId());
        return response;
    }
}
