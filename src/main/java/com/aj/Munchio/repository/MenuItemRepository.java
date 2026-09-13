package com.aj.Munchio.repository;

import com.aj.Munchio.entity.restaurant.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {
}
