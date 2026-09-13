package com.aj.Munchio.entity.restaurant;

import com.aj.Munchio.entity.common.BaseEntity;
import com.aj.Munchio.entity.user.User;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "restaurant")
public class Restaurant extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 20)
    private String phone;

    private String email;

    @Column(name = "logo_url", columnDefinition = "TEXT")
    private String logoUrl;

    @Column(name = "cuisine_type", length = 100)
    private String cuisineType;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "average_rating", nullable = false, precision = 2, scale = 1)
    private BigDecimal averageRating = BigDecimal.ZERO;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "address_id", nullable = false)
    private RestaurantAddress address;


    public Restaurant() {

    }

    public RestaurantAddress getAddress() {
        return address;
    }

    public void setAddress(RestaurantAddress address) {
        this.address = address;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }
}
