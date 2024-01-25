package com.example.fashion.services;

import java.util.List;

import com.example.fashion.models.Cart;
import com.example.fashion.models.CartItem;
import com.example.fashion.models.Product;
import com.example.fashion.models.User;

public interface CartService {

    List<Cart> getAll();

    Boolean create(Cart cart);

    Cart findByID(Long CartID);

    Boolean update(Cart cart);

    Boolean delete(Long CartID);

}
