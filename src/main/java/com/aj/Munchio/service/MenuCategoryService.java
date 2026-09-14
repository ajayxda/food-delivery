package com.aj.Munchio.service;

import com.aj.Munchio.dto.menu.menucategory.MenuCategoryCreateRequest;
import com.aj.Munchio.dto.menu.menucategory.MenuCategoryResponse;
import com.aj.Munchio.dto.menu.menucategory.MenuCategoryUpdateRequest;
import com.aj.Munchio.dto.menu.menuitem.MenuItemResponse;
import com.aj.Munchio.entity.restaurant.Menu;
import com.aj.Munchio.entity.restaurant.MenuCategory;
import com.aj.Munchio.exception.ResourceNotFoundException;
import com.aj.Munchio.mapper.MenuCategoryMapper;
import com.aj.Munchio.mapper.MenuItemMapper;
import com.aj.Munchio.repository.MenuCategoryRepository;
import com.aj.Munchio.repository.MenuItemRepository;
import com.aj.Munchio.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.UUID;

@Service
public class MenuCategoryService {

    private final MenuCategoryRepository menuCategoryRepository;
    private final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;
    private final MenuCategoryMapper mapper;
    private final MenuItemMapper menuItemMapper;

    public MenuCategoryService(MenuCategoryRepository menuCategoryRepository, MenuRepository menuRepository, MenuItemRepository menuItemRepository, MenuCategoryMapper mapper, MenuItemMapper menuItemMapper) {
        this.menuCategoryRepository = menuCategoryRepository;
        this.menuRepository = menuRepository;
        this.menuItemRepository = menuItemRepository;
        this.mapper = mapper;
        this.menuItemMapper = menuItemMapper;
    }


    @Transactional
    public MenuCategoryResponse createMenuCategory(MenuCategoryCreateRequest request) {
        Menu menu = menuRepository.findById(request.getMenuId()).orElseThrow(()-> new ResourceNotFoundException("Unable to find menu with this id"));
        MenuCategory menuCategory = new MenuCategory();
        menuCategory.setName(request.getName());
        menuCategory.setDisplayOrder(request.getDisplayOrder());
        menuCategory.setMenu(menu);
        MenuCategory savedMenuCategory = menuCategoryRepository.save(menuCategory);
        return mapper.mapToDto(savedMenuCategory);
    }

    @Transactional(readOnly = true)
    public MenuCategoryResponse getMenuCategoryById(UUID id) {
        MenuCategory menuCategory = menuCategoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Unable to find menu with this id"));
        return mapper.mapToDto(menuCategory);
    }

    @Transactional(readOnly = true)
    public List<MenuItemResponse> getMenuItemsByCategory(UUID menuCategoryId) {
        if(!menuCategoryRepository.existsById(menuCategoryId)) {
            throw new ResourceNotFoundException("Unable to find menu category with this id");
        }
        return menuItemRepository.findByCategoryIdOrderByDisplayOrderAsc(menuCategoryId)
                .stream()
                .map(menuItemMapper::mapToDto)
                .toList();
    }

    @Transactional
    public MenuCategoryResponse updateMenuCategory(UUID id, MenuCategoryUpdateRequest request) {
        MenuCategory menuCategory = menuCategoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Unable to find menu with this id"));
        menuCategory.setName(request.getName());
        menuCategory.setDisplayOrder(request.getDisplayOrder());
        return mapper.mapToDto(menuCategoryRepository.save(menuCategory));
    }

    @Transactional
    public void deleteMenuCategory(UUID id) {
        if(!menuCategoryRepository.existsById(id)) {
            throw new ResourceAccessException("No menu category found for this id");
        }
        menuCategoryRepository.deleteById(id);
    }

}
