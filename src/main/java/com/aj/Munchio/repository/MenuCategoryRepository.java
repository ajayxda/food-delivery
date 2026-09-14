package com.aj.Munchio.repository;

import com.aj.Munchio.entity.restaurant.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategory, UUID> {
    List<MenuCategory> findMenuCategoryByMenuId(UUID id);
}
