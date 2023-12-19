package com.example.fashion.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fashion.models.Brand;
import com.example.fashion.repository.BrandRepository;
import com.example.fashion.services.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> getAll() {
        // TODO Auto-generated method stub
        return this.brandRepository.findAll();
    }

    @Override
    public Boolean create(Brand brand) {
        // TODO Auto-generated method stub
        try {
            this.brandRepository.save(brand);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Brand findByID(Long BrandID) {
        return this.brandRepository.findById(BrandID).get();
    }

    @Override
    public Boolean update(Brand brand) {
        try {
            this.brandRepository.save(brand);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Long BrandID) {
        try {
            this.brandRepository.delete(findByID(BrandID));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
