package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fashion.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
