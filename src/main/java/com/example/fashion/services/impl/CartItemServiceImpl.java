package com.example.fashion.services.impl;

import java.util.List;
import java.util.Optional;
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
import com.example.fashion.services.CartItemService;
import com.example.fashion.services.ProductService;
import com.example.fashion.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<CartItem> getAll() {
        return this.cartItemRepository.findAll();
    }

    @Override
    @Transactional
    public Boolean create(Long ProductID, Integer Quantity, Cart cart) {
        try {
            Product product = productService.findByID(ProductID);

            // Nếu chưa có giỏ hàng, tạo mới giỏ hàng
            cart = new Cart();
            cart.setCartID((long) 1);
            cart.setUser(userRepository.findByUsername("ad"));
            this.cartRepository.save(cart);
            
            // int totalItems = totalsItem(cart.getCartItems());
            // double totalsPrice = totalsPrice(cart.getCartItems());

            // cart.setTotalsPrice(totalsPrice);
            // cart.setTotalsItem(totalItems);

            double totalPrice = (product.getPrice() - product.getSalePrice()) * Quantity;
            CartItem cartItem = new CartItem();
            cartItem.setCarts(cart);
            cartItem.setProducts(product);
            cartItem.setQuantity(Quantity);
            cartItem.setTotalsPrice(totalPrice);
            this.cartItemRepository.save(cartItem);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CartItem findByID(Long CI_ID) {
        return this.cartItemRepository.findById(CI_ID).get();
    }

    @Override
    public Boolean update(CartItem cartItem) {
        try {
            this.cartItemRepository.save(cartItem);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Long CI_ID) {
        try {
            this.cartItemRepository.delete(findByID(CI_ID));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private int totalsItem(Set<CartItem> cartItems){
        int totalItems = 0;
        for(CartItem item : cartItems){
            totalItems += item.getQuantity();
        }
        return totalItems;
    }

    private double totalsPrice(Set<CartItem> cartItems){
        double totalPrice = 0.0;

        for(CartItem item : cartItems){
            totalPrice += item.getTotalsPrice();
        }

        return totalPrice;
    }

}
