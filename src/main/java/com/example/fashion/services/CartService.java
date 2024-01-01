package com.example.fashion.services;


import com.example.fashion.models.Cart;
import com.example.fashion.models.Product;
import com.example.fashion.models.User;

public interface CartService {

    Cart addItemToCart(Long ProductID, String sessionToken, int Quantity);

    Cart addToExistingCart(Long ProductID, String sessionToken, int Quantity);

    Cart updateItemInCart(Long ProductID, String sessionToken, int Quantity);

    Cart deleteItemFromCart(Long ProductID, String sessionToken);
   
}
