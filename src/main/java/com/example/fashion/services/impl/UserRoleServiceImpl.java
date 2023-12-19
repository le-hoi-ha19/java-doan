package com.example.fashion.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fashion.models.Role;
import com.example.fashion.models.UserRole;
import com.example.fashion.repository.UserRoleRepository;
import com.example.fashion.services.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserRole> getAll() {
        return userRoleRepository.findAll();
    }

    @Override
    @Transactional
    public boolean create(UserRole userRole) {
        try {
            userRoleRepository.save(userRole);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public UserRole findByID(Long id) {
        return this.userRoleRepository.findById(id).get();
    }
}
