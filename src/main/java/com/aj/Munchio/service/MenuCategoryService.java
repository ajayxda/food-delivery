package com.aj.Munchio.service;

import com.aj.Munchio.dto.menu.menuCategory.MenuCategoryCreateRequest;
import com.aj.Munchio.dto.menu.menuCategory.MenuCategoryResponse;
import com.aj.Munchio.dto.menu.menuCategory.MenuCategoryUpdateRequest;
import com.aj.Munchio.entity.restaurant.Menu;
import com.aj.Munchio.entity.restaurant.MenuCategory;
import com.aj.Munchio.exception.ResourceNotFoundException;
import com.aj.Munchio.mapper.MenuCategoryMapper;
import com.aj.Munchio.repository.MenuCategoryRepository;
import com.aj.Munchio.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.util.UUID;

@Service
public class MenuCategoryService {

    private final MenuCategoryRepository menuCategoryRepository;
    private final MenuRepository menuRepository;
    private final MenuCategoryMapper mapper;

    public MenuCategoryService(MenuCategoryRepository menuCategoryRepository, MenuRepository menuRepository, MenuCategoryMapper mapper) {
        this.menuCategoryRepository = menuCategoryRepository;
        this.menuRepository = menuRepository;
        this.mapper = mapper;
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
