package com.example.fashion.services;

import java.util.List;

import com.example.fashion.models.Category;

public interface CategoryService {
	List<Category> getAll();

	Boolean create(Category category);

	Category findByID(Integer CatID);

	Boolean update(Category category);

	Boolean delete(Integer CatID);

	// Category findByCatName(String CatName);
}
