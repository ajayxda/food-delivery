package com.aj.Munchio.mapper;

import com.aj.Munchio.dto.address.AddressResponse;
import com.aj.Munchio.dto.restaurant.RestaurantResponse;
import com.aj.Munchio.entity.restaurant.Restaurant;
import com.aj.Munchio.entity.restaurant.RestaurantAddress;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {
    public RestaurantResponse mapToDTO(Restaurant restaurant) {
        RestaurantResponse dto = new RestaurantResponse();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setEmail(restaurant.getEmail());
        dto.setPhone(restaurant.getPhone());
        dto.setDescription(restaurant.getDescription());
        dto.setCuisineType(restaurant.getCuisineType());
        dto.setLogoUrl(restaurant.getLogoUrl());
        dto.setIsActive(restaurant.isActive());
        dto.setAverageRating(restaurant.getAverageRating());
        dto.setOwnerId(restaurant.getOwner().getId());
        dto.setCreatedAt(restaurant.getCreatedAt());
        dto.setUpdatedAt(restaurant.getUpdatedAt());
        if (restaurant.getAddress() != null) {
            dto.setAddress(mapAddressToDTO(restaurant.getAddress()));
        }
        return dto;
    }

    private AddressResponse mapAddressToDTO(RestaurantAddress address) {
        AddressResponse dto = new AddressResponse();
        dto.setId(address.getId());
        dto.setAddressLine1(address.getAddressLine1());
        dto.setAddressLine2(address.getAddressLine2());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setPostalCode(address.getPostalCode());
        dto.setLatitude(address.getLatitude());
        dto.setLongitude(address.getLongitude());
        return dto;
    }
}
