package com.example.fashion.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fashion.models.Cart;
import com.example.fashion.models.CartItem;
import com.example.fashion.models.Product;
import com.example.fashion.models.User;
import com.example.fashion.repository.CartItemRepository;
import com.example.fashion.repository.CartRepository;
import com.example.fashion.repository.ProductRepository;
import com.example.fashion.repository.UserRepository;
import com.example.fashion.services.CartService;
import com.example.fashion.services.ProductService;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Override
    public List<Cart> getAll() {
        return this.cartRepository.findAll();
    }

    @Override
    public Cart findByID(Long CartID) {
        return this.cartRepository.findById(CartID).get();
    }

    @Override
    public Boolean create(Cart cart) {
        try {
            int totalQuantity = this.cartItemRepository.sumQuantityByCart(cart.getCartID());
            cart.setTotalsItem(totalQuantity);
            this.cartRepository.save(cart);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(Cart cart) {
        try {
            this.cartRepository.save(cart);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Long CartID) {
        try {
            this.cartRepository.delete(findByID(CartID));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    

}
