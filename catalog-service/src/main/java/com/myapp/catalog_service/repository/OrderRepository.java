package com.myapp.catalog_service.repository;

import com.myapp.catalog_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCategory(String category);
}
