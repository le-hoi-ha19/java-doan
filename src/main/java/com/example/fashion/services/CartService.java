package com.example.fashion.services;


import com.example.fashion.models.Cart;
import com.example.fashion.models.Product;
import com.example.fashion.models.User;

public interface CartService {

    Boolean addItemToCart(Product product, Integer quantity);

    Boolean updateItemInCart(Product product, Integer quantity);

    Boolean deleteItemFromCart(Product product);
   
}
