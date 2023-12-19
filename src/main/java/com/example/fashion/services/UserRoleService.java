package com.example.fashion.services;

import java.util.List;

import com.example.fashion.models.Role;
import com.example.fashion.models.UserRole;

public interface UserRoleService {
    List<UserRole> getAll();

    UserRole findByID(Long id);

    boolean create(UserRole userRole);
}
