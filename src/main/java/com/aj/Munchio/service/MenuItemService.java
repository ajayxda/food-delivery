package com.aj.Munchio.service;

import com.aj.Munchio.dto.menu.menuitem.MenuItemCreateRequest;
import com.aj.Munchio.dto.menu.menuitem.MenuItemResponse;
import com.aj.Munchio.dto.menu.menuitem.MenuItemUpdateRequest;
import com.aj.Munchio.entity.restaurant.MenuCategory;
import com.aj.Munchio.entity.restaurant.MenuItem;
import com.aj.Munchio.exception.ResourceNotFoundException;
import com.aj.Munchio.mapper.MenuItemMapper;
import com.aj.Munchio.repository.MenuCategoryRepository;
import com.aj.Munchio.repository.MenuItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.UUID;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuCategoryRepository menuCategoryRepository;
    private final MenuItemMapper menuItemMapper;

    public MenuItemService(MenuItemRepository menuItemRepository, MenuCategoryRepository menuCategoryRepository, MenuItemMapper menuItemMapper) {
        this.menuItemRepository = menuItemRepository;
        this.menuCategoryRepository = menuCategoryRepository;
        this.menuItemMapper = menuItemMapper;
    }

    @Transactional
    public MenuItemResponse createMenuItem(MenuItemCreateRequest menuItemCreateRequest) {
        MenuCategory menuCategory = menuCategoryRepository.findById(menuItemCreateRequest.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("category not found with this id"));
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemCreateRequest.getName());
        menuItem.setDescription(menuItemCreateRequest.getDescription());
        menuItem.setVeg(menuItemCreateRequest.isVeg());
        menuItem.setPrice(menuItemCreateRequest.getPrice());
        menuItem.setDisplayOrder(menuItemCreateRequest.getDisplayOrder());
        menuItem.setImageUrl(menuItemCreateRequest.getImageUrl());
        menuItem.setCategory(menuCategory);
        return menuItemMapper.mapToDto(menuItem);
    }

    @Transactional
    public MenuItemResponse updateMenuItem(UUID id, MenuItemUpdateRequest menuItemUpdateRequest) {
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("menuitem not found with this id"));
        menuItem.setName(menuItemUpdateRequest.getName());
        menuItem.setDescription(menuItemUpdateRequest.getDescription());
        menuItem.setVeg(menuItemUpdateRequest.isVeg());
        menuItem.setPrice(menuItemUpdateRequest.getPrice());
        menuItem.setDisplayOrder(menuItemUpdateRequest.getDisplayOrder());
        menuItem.setImageUrl(menuItemUpdateRequest.getImageUrl());
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        return menuItemMapper.mapToDto(savedMenuItem);
    }

    public MenuItemResponse getMenuItemById(UUID menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(()-> new ResourceNotFoundException("Menu Item not found with this id"));
        return menuItemMapper.mapToDto(menuItem);
    }


    @Transactional
    public void deleteMenuItem(UUID id) {
        if(menuItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Menu Item not found with this id");
        }
        menuItemRepository.deleteById(id);
    }
}
