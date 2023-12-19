package com.example.fashion.services;


import com.example.fashion.models.Cart;
import com.example.fashion.models.Product;

public interface CartService {

    Cart addItemToCart(Product product, Integer Quantity);

    Cart updateItemInCart(Product product, Integer Quantity);

    Cart deleteItemFromCart(Product product);
    
}
