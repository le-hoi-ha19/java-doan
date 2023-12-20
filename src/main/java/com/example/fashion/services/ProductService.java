package com.example.fashion.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.fashion.models.Product;

public interface ProductService {
	List<Product> getAll();

	// List<Product> findTop5ByOrderByidDesc(); // Add this method
	Boolean create(Product Product);

	Product findByID(Long ProductID);

	Boolean update(Product products);

	Boolean delete(Long ProductID);

	// List<Product> searchProduct(String keyword);

	Page<Product> getAll(Long pageNo);
}
