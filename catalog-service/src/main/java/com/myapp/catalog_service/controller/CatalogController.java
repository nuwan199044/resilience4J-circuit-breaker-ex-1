package com.myapp.catalog_service.controller;

import com.myapp.catalog_service.entity.Order;
import com.myapp.catalog_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class CatalogController {

    private final OrderRepository orderRepository;

    @GetMapping
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{category}")
    public List<Order> getOrderByCategory(@PathVariable String category) {
        List<Order> orders = orderRepository.findByCategory(category);
        return orders;
    }
}
