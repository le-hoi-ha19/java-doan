package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fashion.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
