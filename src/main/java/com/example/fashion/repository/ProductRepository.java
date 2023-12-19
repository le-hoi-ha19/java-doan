package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fashion.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
