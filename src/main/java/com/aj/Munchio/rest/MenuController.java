package com.aj.Munchio.rest;

import com.aj.Munchio.dto.menu.MenuCreateRequest;
import com.aj.Munchio.dto.menu.MenuResponse;
import com.aj.Munchio.dto.menu.MenuUpdateRequest;
import com.aj.Munchio.dto.menu.menuCategory.MenuCategoryResponse;
import com.aj.Munchio.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/menus")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<MenuResponse> getMenu(@PathVariable UUID menuId) {
        MenuResponse menu = menuService.getMenuById(menuId);
        return ResponseEntity.status(HttpStatus.OK).body(menu);
    }

    @GetMapping("/{menuId}/categories")
    public ResponseEntity<List<MenuCategoryResponse>> getMenuCategories(@PathVariable UUID menuId) {
        List<MenuCategoryResponse> menus = menuService.getMenuCategories(menuId);
        return ResponseEntity.status(HttpStatus.OK).body(menus);
    }

    @PostMapping
    public ResponseEntity<MenuResponse> createMenu(@Valid @RequestBody MenuCreateRequest request) {
        MenuResponse menu = menuService.createMenu(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(menu);
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<MenuResponse> updateMenu(@PathVariable UUID menuId,@Valid @RequestBody MenuUpdateRequest request) {
        MenuResponse menu = menuService.updateMenu(menuId, request);
        return ResponseEntity.status(HttpStatus.OK).body(menu);
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> deleteMenu(@PathVariable UUID menuId) {
        menuService.deleteMenu(menuId);
        return ResponseEntity.noContent().build();
    }

}
