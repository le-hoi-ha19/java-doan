package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fashion.models.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
    
}
