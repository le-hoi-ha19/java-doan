package com.example.fashion.services;

import java.util.List;

import com.example.fashion.models.Product;

public interface ProductService {
	List<Product> getAll();

	// List<Product> findTop5ByOrderByidDesc(); // Add this method
	Boolean create(Product Product);

	Product findByID(Long ProductID);

	Boolean update(Product products);

	Boolean delete(Long ProductID);
}
