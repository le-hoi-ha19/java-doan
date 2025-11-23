package com.example.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fashion.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	User findByFullname(String fullname);

	@Query("SELECT DISTINCT u FROM User u JOIN FETCH u.userRoles ur JOIN FETCH ur.role r WHERE r.name = 'CUSTOMER'")
    List<User> findCustomerUsers();
	
	@Query(value = "SELECT COUNT(u) FROM User u WHERE EXISTS (SELECT ur FROM UserRole ur WHERE ur.user = u AND ur.role.name = 'CUSTOMER')")
    long countCustomerUsers();

    @Query(value = "SELECT u FROM User u WHERE EXISTS (SELECT ur FROM UserRole ur WHERE ur.user = u AND ur.role.name = 'ADMIN')")
    List<User> findAdminUsers();
}
