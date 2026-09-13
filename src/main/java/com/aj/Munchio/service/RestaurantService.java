package com.aj.Munchio.service;

import com.aj.Munchio.dto.menu.MenuResponse;
import com.aj.Munchio.dto.restaurant.RestaurantCreateRequest;
import com.aj.Munchio.dto.restaurant.RestaurantResponse;
import com.aj.Munchio.dto.restaurant.RestaurantUpdateRequest;
import com.aj.Munchio.entity.restaurant.Menu;
import com.aj.Munchio.entity.restaurant.Restaurant;
import com.aj.Munchio.entity.restaurant.RestaurantAddress;
import com.aj.Munchio.entity.user.User;
import com.aj.Munchio.exception.ResourceNotFoundException;
import com.aj.Munchio.mapper.MenuMapper;
import com.aj.Munchio.mapper.RestaurantMapper;
import com.aj.Munchio.repository.MenuRepository;
import com.aj.Munchio.repository.RestaurantRepository;
import com.aj.Munchio.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final RestaurantMapper restaurantMapper;
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    public RestaurantService(RestaurantRepository restaurantRepository, UserRepository userRepository, RestaurantMapper restaurantMapper, MenuRepository menuRepository, MenuMapper menuMapper) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.restaurantMapper = restaurantMapper;
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    @Transactional
    public RestaurantResponse saveRestaurant(RestaurantCreateRequest request) {
        User user = userRepository.findById(request.getOwnerId()).orElseThrow(()-> new ResourceNotFoundException("User not found with this id"));
        RestaurantAddress restaurantAddress = new RestaurantAddress();
        restaurantAddress.setAddressLine1(request.getAddress().getAddressLine1());
        restaurantAddress.setAddressLine2(request.getAddress().getAddressLine2());
        restaurantAddress.setCity(request.getAddress().getCity());
        restaurantAddress.setState(request.getAddress().getState());
        restaurantAddress.setPostalCode(request.getAddress().getPostalCode());
        restaurantAddress.setLatitude(request.getAddress().getLatitude());
        restaurantAddress.setLongitude(request.getAddress().getLongitude());

        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setDescription(request.getDescription());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setPhone(request.getPhone());
        restaurant.setEmail(request.getEmail());
        restaurant.setLogoUrl(request.getLogoUrl());
        restaurant.setAddress(restaurantAddress);
        restaurant.setOwner(user);
        Restaurant savedRestaurant =  restaurantRepository.save(restaurant);

        return restaurantMapper.mapToDTO(savedRestaurant);
    }

    @Transactional
    public void deleteRestaurant(UUID id) {
        if(!restaurantRepository.existsById(id)) {
            throw new ResourceNotFoundException("Unable to find restaurant with this id "+id);
        }
        restaurantRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<RestaurantResponse> getAllRestaurant() {
        List<Restaurant> restaurant = restaurantRepository.findAll();
        return restaurant.stream().map(restaurantMapper::mapToDTO).toList();
    }

    @Transactional(readOnly = true)
    public RestaurantResponse getRestaurantById(UUID restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()-> new ResourceNotFoundException("Unable to find restaurant with this id "+restaurantId));
        return restaurantMapper.mapToDTO(restaurant);
    }

    @Transactional
    public RestaurantResponse updateRestaurant(UUID id, RestaurantUpdateRequest restaurant) {
        Restaurant existing =  restaurantRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Unable to find restaurant with this id "+id));
        existing.setName(restaurant.getName());
        existing.setCuisineType(restaurant.getCuisineType());
        existing.setDescription(restaurant.getDescription());
        existing.setPhone(restaurant.getPhone());
        existing.setEmail(restaurant.getEmail());
        existing.setLogoUrl(restaurant.getLogoUrl());

        return restaurantMapper.mapToDTO(existing);
    }

    public List<MenuResponse> findMenus(UUID restaurantId) {
        List<Menu> menus = menuRepository.findMenuByRestaurantId(restaurantId);
        return menus.stream().map(menuMapper::mapToDto).toList();

    }
}
