package com.example.fashion.services;

import java.util.List;
import java.util.Set;

import com.example.fashion.models.Cart;
import com.example.fashion.models.CartItem;
import com.example.fashion.models.User;

public interface CartItemService {
    List<CartItem> getAll();

    Boolean create(Long ProductID, Integer Quantity, User user);

    CartItem findByID(Long CI_ID);

    Boolean update(Long ProductID, Integer Quantity, User user);

    Boolean delete(Long ProductID, User user);

    List<CartItem> findByUser(Long userID);
    
    

}
