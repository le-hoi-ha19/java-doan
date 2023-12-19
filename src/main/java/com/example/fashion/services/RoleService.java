package com.example.fashion.services;

import java.util.List;

import com.example.fashion.models.Role;

public interface RoleService {

    List<Role> getAll();

    Role findByID(Long id);

    Role findByName(String name);

}
