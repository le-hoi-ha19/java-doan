package com.example.fashion.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.example.fashion.models.Brand;
import com.example.fashion.models.Category;
import com.example.fashion.models.Product;

public interface ProductService {
    
    Page<Product> getAll(Long pageNo);
    
    List<Product> getAll();
    
    Product findByID(Long productId);
    
    List<Product> findByCategory(Category category);
    
    List<Product> findByBrand(Brand brand);
    
    long countTotalProducts();
    
    Boolean create(Product product);
    
    Boolean update(Product product);
    
    Boolean delete(Long productId);
    
    List<Product> list6Products();
    
    long countOutOfStockProducts();
    
    long countLowStockProducts();
}
