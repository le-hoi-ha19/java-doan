package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fashion.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

    Cart findBySessionToken(String sessionToken);
    
}
