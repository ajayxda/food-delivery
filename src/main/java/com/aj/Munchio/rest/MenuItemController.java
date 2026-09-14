package com.aj.Munchio.rest;


import com.aj.Munchio.dto.menu.menuitem.MenuItemCreateRequest;
import com.aj.Munchio.dto.menu.menuitem.MenuItemResponse;
import com.aj.Munchio.dto.menu.menuitem.MenuItemUpdateRequest;
import com.aj.Munchio.service.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {
    private final MenuItemService menuItemService;


    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/{menuItemId}")
    public ResponseEntity<MenuItemResponse> getMenuItemById(@PathVariable UUID menuItemId) {
        MenuItemResponse menuItemResponse = menuItemService.getMenuItemById(menuItemId);
        return ResponseEntity.status(HttpStatus.OK).body(menuItemResponse);
    }

    @PostMapping
    public ResponseEntity<MenuItemResponse> createMenuItem(@Valid @RequestBody MenuItemCreateRequest request) {
        MenuItemResponse menuItemResponse = menuItemService.createMenuItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItemResponse);
    }

    @PutMapping("/{menuItemId}")
    public ResponseEntity<MenuItemResponse> updateMenuItem(@PathVariable UUID menuItemId, @Valid @RequestBody MenuItemUpdateRequest request) {
        MenuItemResponse menuItemResponse = menuItemService.updateMenuItem(menuItemId, request);
        return ResponseEntity.status(HttpStatus.OK).body(menuItemResponse);
    }

    @DeleteMapping("/{menuItemId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable UUID menuItemId) {
        menuItemService.deleteMenuItem(menuItemId);
        return ResponseEntity.noContent().build();
    }

}
