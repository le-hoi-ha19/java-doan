package com.example.fashion.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fashion.models.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // @Query("SELECT p FROM Product p WHERE p.ProductName LIKE %?1%")
    // List<Product> searchProduct(String keyword);
}
