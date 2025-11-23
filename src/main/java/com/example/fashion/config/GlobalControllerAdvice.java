package com.example.fashion.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.fashion.models.Brand;
import com.example.fashion.models.Category;
import com.example.fashion.services.BrandService;
import com.example.fashion.services.CategoryService;

@ControllerAdvice(basePackages = "com.example.fashion.controller.customer")
public class GlobalControllerAdvice {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @ModelAttribute("categories")
    public List<Category> populateCategories() {
        try {
            List<Category> categories = categoryService.getAll();
            return categories != null ? categories : new java.util.ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }

    @ModelAttribute("listBra")
    public List<Brand> populateBrands() {
        try {
            List<Brand> brands = brandService.getAll();
            return brands != null ? brands : new java.util.ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }
}
