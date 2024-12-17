package com.example.fashion.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fashion.models.Category;
import com.example.fashion.repository.CategoryRepository;
import com.example.fashion.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        // TODO Auto-generated method stub
        return this.categoryRepository.findAll();
    }

    @Override
    public Boolean create(Category category) {
        // TODO Auto-generated method stub
        try {
            this.categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Category findByID(Integer CatID) {
        return this.categoryRepository.findById(CatID).get();
    }

    @Override
    public Category findBySlug(String Slug) {
        return this.categoryRepository.findBySlug(Slug).get();
    }

    @Override
    public Boolean update(Category category) {
        try {
            this.categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Integer CatID) {
        try {
            this.categoryRepository.delete(findByID(CatID));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // @Override
    // public Category findByCatName(String CatName) {
    //     return categoryRepository.findByCatName(CatName);

    // }

}
