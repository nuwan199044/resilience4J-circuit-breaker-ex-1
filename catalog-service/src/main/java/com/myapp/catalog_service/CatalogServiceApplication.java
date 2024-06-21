package com.myapp.catalog_service;

import com.myapp.catalog_service.entity.Order;
import com.myapp.catalog_service.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Stream;

@SpringBootApplication
public class CatalogServiceApplication {

	@Autowired
	OrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(CatalogServiceApplication.class, args);
	}

	@PostConstruct
	public void initOrdersTable() {
		orderRepository.saveAll(Stream.of(
				new Order(0,"mobile","electronics","white",22000),
				new Order(0,"Laptop","electronics","gray",100000),
				new Order(0,"T-Shirt","clothes","blue",1800))
				.toList());
	}

}
