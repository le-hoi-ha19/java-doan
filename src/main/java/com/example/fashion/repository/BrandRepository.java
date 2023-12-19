package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fashion.models.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    
}
