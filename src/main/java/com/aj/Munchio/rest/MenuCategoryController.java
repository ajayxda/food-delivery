package com.aj.Munchio.rest;

import com.aj.Munchio.dto.menu.menuCategory.MenuCategoryCreateRequest;
import com.aj.Munchio.dto.menu.menuCategory.MenuCategoryResponse;
import com.aj.Munchio.dto.menu.menuCategory.MenuCategoryUpdateRequest;
import com.aj.Munchio.service.MenuCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
