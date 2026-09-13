package com.aj.Munchio.dto.restaurant;

import com.aj.Munchio.dto.address.RestaurantAddressRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class RestaurantCreateRequest {

    @NotNull
    private UUID ownerId;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;

    @NotBlank
    private String logoUrl;

    @NotBlank
    private String cuisineType;

    @NotNull
    @Valid
    private RestaurantAddressRequest address;

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public RestaurantAddressRequest getAddress() {
        return address;
    }

    public void setAddress(RestaurantAddressRequest address) {
        this.address = address;
    }
}
