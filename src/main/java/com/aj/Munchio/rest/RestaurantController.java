package com.aj.Munchio.rest;


import com.aj.Munchio.dto.menu.MenuResponse;
import com.aj.Munchio.dto.restaurant.RestaurantCreateRequest;
import com.aj.Munchio.dto.restaurant.RestaurantResponse;
import com.aj.Munchio.dto.restaurant.RestaurantUpdateRequest;
import com.aj.Munchio.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("")
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurant() {
        List<RestaurantResponse> restaurant = restaurantService.getAllRestaurant();
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponse> getRestaurantById(@PathVariable UUID restaurantId) {
        RestaurantResponse restaurant = restaurantService.getRestaurantById(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    @GetMapping("/{restaurantId}/menus")
    public ResponseEntity<List<MenuResponse>> getMenus(@PathVariable UUID restaurantId) {
        List<MenuResponse> menuResponseList = restaurantService.findMenus(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(menuResponseList);
    }

    @PostMapping("")
    public ResponseEntity<RestaurantResponse> createRestaurant(@Valid @RequestBody RestaurantCreateRequest restaurant) {
        RestaurantResponse newRestaurant = restaurantService.saveRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRestaurant);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable UUID restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponse> updateRestaurant(@PathVariable UUID restaurantId,@Valid @RequestBody RestaurantUpdateRequest restaurant) {
        RestaurantResponse restaurantData = restaurantService.updateRestaurant(restaurantId,restaurant);

        return ResponseEntity.status(HttpStatus.OK).body(restaurantData);
    }
}
