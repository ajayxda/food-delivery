package com.aj.Munchio.service;

import com.aj.Munchio.dto.menu.MenuCreateRequest;
import com.aj.Munchio.dto.menu.MenuResponse;
import com.aj.Munchio.dto.menu.MenuUpdateRequest;
import com.aj.Munchio.dto.menu.menucategory.MenuCategoryResponse;
import com.aj.Munchio.entity.restaurant.Menu;
import com.aj.Munchio.entity.restaurant.MenuCategory;
import com.aj.Munchio.entity.restaurant.Restaurant;
import com.aj.Munchio.exception.ResourceNotFoundException;
import com.aj.Munchio.mapper.MenuCategoryMapper;
import com.aj.Munchio.mapper.MenuMapper;
import com.aj.Munchio.repository.MenuCategoryRepository;
import com.aj.Munchio.repository.MenuRepository;
import com.aj.Munchio.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuMapper menuMapper;
    private final MenuCategoryMapper menuCategoryMapper;
    private final MenuCategoryRepository menuCategoryRepository;


    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository, MenuMapper menuMapper, MenuCategoryMapper menuCategoryMapper, MenuCategoryRepository menuCategoryRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuMapper = menuMapper;
        this.menuCategoryMapper = menuCategoryMapper;
        this.menuCategoryRepository = menuCategoryRepository;
    }

    @Transactional
    public MenuResponse createMenu(MenuCreateRequest request) {
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId()).orElseThrow(()-> new ResourceNotFoundException(("Unable to find Restaurant with this id")));
        Menu menu = new Menu();
        menu.setName(request.getName());
        menu.setRestaurant(restaurant);
        Menu savedMenu = menuRepository.save(menu);
        return menuMapper.mapToDto(savedMenu);
    }

    @Transactional
    public MenuResponse updateMenu(UUID id,MenuUpdateRequest request) {
        Menu menu = menuRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(("Unable for find Menu with this id")));
        menu.setName(request.getName());
        return menuMapper.mapToDto(menu);
    }

    @Transactional
    public void deleteMenu(UUID id) {
        if(!menuRepository.existsById(id)) {
            throw new ResourceNotFoundException(("Unable to find Menu with this id"));
        }
        menuRepository.deleteById(id);
    }

    public List<MenuCategoryResponse> getMenuCategories(UUID menuId) {
        List<MenuCategory> menuCategories = menuCategoryRepository.findMenuCategoryByMenuId(menuId);
        return menuCategories.stream().map(menuCategoryMapper::mapToDto).toList();
    }

    @Transactional(readOnly = true)
    public MenuResponse getMenuById(UUID menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(()->new ResourceNotFoundException("Unable for find Menu with this id"));
        return menuMapper.mapToDto(menu);
    }
}

