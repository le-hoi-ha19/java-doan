package com.example.fashion.services;

import com.example.fashion.models.User;

public interface UserService {
    User findByUsername(String username);
    User findByID(Long id);
    // Boolean create(User user);
    Boolean createWithUserRole(User user);

    Boolean createWithUserRoleCUSTOMER(User user);

}
