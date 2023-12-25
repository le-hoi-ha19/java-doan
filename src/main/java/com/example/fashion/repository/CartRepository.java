package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fashion.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

    Cart findCartByUserIdIsNull();
    
}
