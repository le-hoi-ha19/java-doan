package com.example.fashion.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.fashion.models.Brand;
import com.example.fashion.models.Category;
import com.example.fashion.services.BrandService;
import com.example.fashion.services.CategoryService;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @ModelAttribute("categories")
    public List<Category> populateCategories() {
        return categoryService.getAll();
    }

    @ModelAttribute("listBra")
    public List<Brand> populateBrands() {
        return brandService.getAll();
    }
}
