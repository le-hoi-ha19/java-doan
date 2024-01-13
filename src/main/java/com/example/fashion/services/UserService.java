package com.example.fashion.services;

import java.util.List;

import com.example.fashion.models.User;

public interface UserService {
    User findByUsername(String username);

    User findByID(Long id);

    Boolean createWithUserRole(User user);

    Boolean createWithUserRoleCUSTOMER(User user);

    List<User> getCustomerUsers();

    Boolean delete (Long id);

	long countTotalUsers();

}