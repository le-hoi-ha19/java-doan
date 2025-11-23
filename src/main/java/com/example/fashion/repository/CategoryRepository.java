package com.example.fashion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fashion.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Category findByCatName(String CatName);
    // Tạo phương thức tùy chỉnh để tìm kiếm theo slug
    Optional<Category> findBySlug(String slug);

    @Query(value = "SELECT c FROM Category c ORDER BY c.CatID DESC")
    List<Category> findAllOrderByIdDesc();
}
