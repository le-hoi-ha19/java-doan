package com.example.fashion.services;

import java.util.List;
import java.util.Set;

import com.example.fashion.models.Cart;
import com.example.fashion.models.CartItem;
import com.example.fashion.models.User;

public interface CartItemService {
    List<CartItem> getAll();

    Boolean create(Long ProductID, Integer Quantity, Cart cart);

    CartItem findByID(Long CI_ID);

    Boolean update(CartItem cartItem);

    Boolean delete(Long CI_ID);

    // CartItem findCartItem(Set<CartItem> cartItems, Long ProductID);

}
