package com.aj.Munchio.rest;

import com.aj.Munchio.dto.menu.menucategory.MenuCategoryCreateRequest;
import com.aj.Munchio.dto.menu.menucategory.MenuCategoryResponse;
import com.aj.Munchio.dto.menu.menucategory.MenuCategoryUpdateRequest;
import com.aj.Munchio.dto.menu.menuitem.MenuItemResponse;
import com.aj.Munchio.service.MenuCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/menu-categories")
public class MenuCategoryController {

    private final MenuCategoryService menuCategoryService;

    public MenuCategoryController(MenuCategoryService menuCategoryService) {
        this.menuCategoryService = menuCategoryService;
    }

    @GetMapping("/{menuCategoryId}")
    public ResponseEntity<MenuCategoryResponse> getMenuById(@PathVariable  UUID menuCategoryId) {
        MenuCategoryResponse menuCategoryResponse = menuCategoryService.getMenuCategoryById(menuCategoryId);
        return ResponseEntity.status(HttpStatus.OK).body(menuCategoryResponse);
    }

    @GetMapping("/{menuCategoryId}/menu-items")
    public ResponseEntity<List<MenuItemResponse>> getMenuItemsByCategory(@PathVariable UUID menuCategoryId) {
        List<MenuItemResponse> menuItems = menuCategoryService.getMenuItemsByCategory(menuCategoryId);
        return ResponseEntity.status(HttpStatus.OK).body(menuItems);
    }

    @PostMapping
    public ResponseEntity<MenuCategoryResponse> createMenu(@Valid @RequestBody MenuCategoryCreateRequest request) {
        MenuCategoryResponse menuCategoryResponse = menuCategoryService.createMenuCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuCategoryResponse);
    }

    @PutMapping("/{menuCategoryId}")
    public ResponseEntity<MenuCategoryResponse> updateMenu(@PathVariable UUID menuCategoryId,@Valid @RequestBody MenuCategoryUpdateRequest request) {
        MenuCategoryResponse menuCategoryResponse = menuCategoryService.updateMenuCategory(menuCategoryId, request);
        return ResponseEntity.status(HttpStatus.OK).body(menuCategoryResponse);
    }

    @DeleteMapping("/{menuCategoryId}")
    public ResponseEntity<Void> deleteMenu(@PathVariable UUID menuCategoryId) {
        menuCategoryService.deleteMenuCategory(menuCategoryId);
        return ResponseEntity.noContent().build();
    }

}
