package com.example.fashion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fashion.models.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    // Tạo phương thức tùy chỉnh để tìm kiếm theo slug
    Optional<Brand> findBySlug(String slug);
}
