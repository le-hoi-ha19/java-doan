package com.example.fashion.services;


import com.example.fashion.models.Cart;
import com.example.fashion.models.Product;
import com.example.fashion.models.User;

public interface CartService {

    Boolean addItemToCart(Product product, Integer Quantity, User user);

    Boolean updateItemInCart(Product product, Integer Quantity, User user);

    Boolean deleteItemFromCart(Product product, User user);
   
}
