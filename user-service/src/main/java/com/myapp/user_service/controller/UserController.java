package com.myapp.user_service.controller;

import com.myapp.user_service.dto.OrderDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    @Lazy
    RestTemplate restTemplate;

    public static final String USER_SERVICE = "userService";

    public static final String BASE_URL = "http://localhost:8081/orders";

    @GetMapping("/display-orders")
    @CircuitBreaker(name = USER_SERVICE, fallbackMethod = "getAllAvailableProducts")
    public List<OrderDTO> displayOrders(@RequestParam("category") String category) {
        String url = category == null || category.equals("") ? BASE_URL : BASE_URL + "/" + category;
        List<OrderDTO> orderDTOS = restTemplate.getForObject(url, List.class);
        return orderDTOS;
    }

    public List<OrderDTO> getAllAvailableProducts(Exception e){
        return Stream.of(
                new OrderDTO(119, "LED TV", "electronics", "white", 45000),
                new OrderDTO(345, "Headset", "electronics", "black", 7000),
                new OrderDTO(475, "Sound bar", "electronics", "black", 13000),
                new OrderDTO(574, "Puma Shoes", "foot wear", "black & white", 4600),
                new OrderDTO(678, "Vegetable chopper", "kitchen", "blue", 999),
                new OrderDTO(532, "Oven Gloves", "kitchen", "gray", 745)
        ).collect(Collectors.toList());
    }

}
