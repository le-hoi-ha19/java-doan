package com.example.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fashion.models.Brand;
import com.example.fashion.models.Category;
import com.example.fashion.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query(value = "SELECT p FROM Product p ORDER BY p.ProductID DESC LIMIT 9")
    List<Product> list6Products();

    @Query(value = "SELECT COUNT(p) FROM Product p")
    long countTotalProducts();

    Product findByCategory(Category category);

    Product findByBrand(Brand brand);
}
