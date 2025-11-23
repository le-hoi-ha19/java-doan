package com.example.fashion.services;

import java.util.List;

import com.example.fashion.models.Brand;

public interface BrandService {
	List<Brand> getAll();
	Boolean create(Brand Brand);
	Brand findByID(Long BrandID);
	Brand findBySlug(String Slug);
	Boolean update(Brand brand);
	Boolean delete(Long BrandID);
}
