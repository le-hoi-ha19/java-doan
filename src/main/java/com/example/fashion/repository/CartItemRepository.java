package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fashion.models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
}
