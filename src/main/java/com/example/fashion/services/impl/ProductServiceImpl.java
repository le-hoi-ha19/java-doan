package com.example.fashion.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fashion.models.Product;
import com.example.fashion.repository.ProductRepository;
import com.example.fashion.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        // TODO Auto-generated method stub
        return this.productRepository.findAll();
    }

    // @Override
    // public List<Product> findTop5ByOrderByidDesc() {
    //     // TODO Auto-generated method stub
    //     return this.productRepository.findAll();
    // }

    @Override
    public Boolean create(Product products) {
        try {
            this.productRepository.save(products);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product findByID(Long ProductID) {
        return this.productRepository.findById(ProductID).get();
    }

    @Override
    public Boolean update(Product products) {
        try {
            this.productRepository.save(products);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Long ProductID) {
        try {
            this.productRepository.delete(findByID(ProductID));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
