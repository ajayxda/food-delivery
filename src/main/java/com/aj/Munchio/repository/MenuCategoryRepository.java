package com.aj.Munchio.repository;

import com.aj.Munchio.dto.menu.menuCategory.MenuCategoryResponse;
import com.aj.Munchio.entity.restaurant.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, UUID> {
    List<MenuCategory> findMenuCategoryByMenuId(UUID id);
}
