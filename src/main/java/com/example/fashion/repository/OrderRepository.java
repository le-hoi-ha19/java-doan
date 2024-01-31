package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fashion.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
