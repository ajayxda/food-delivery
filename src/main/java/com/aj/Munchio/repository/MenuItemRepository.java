package com.aj.Munchio.repository;

import com.aj.Munchio.entity.restaurant.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {

    List<MenuItem> findByCategoryIdOrderByDisplayOrderAsc(UUID categoryId);
}
