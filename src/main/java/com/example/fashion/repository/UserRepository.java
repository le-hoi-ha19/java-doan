package com.example.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fashion.models.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	@Query("SELECT DISTINCT u FROM User u JOIN FETCH u.userRoles ur JOIN FETCH ur.role r WHERE r.name = 'CUSTOMER'")
    List<User> findCustomerUsers();
	
}
