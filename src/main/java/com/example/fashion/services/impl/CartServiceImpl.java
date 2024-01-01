package com.example.fashion.services.impl;

import java.util.HashSet;
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
    public Cart addItemToCart(Long ProductID, String sessionToken, int Quantity) {
        Cart cart = new Cart();
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(Quantity);
		cartItem.setProducts(productService.findByID(ProductID));
		cart.getCartItems().add(cartItem);
		cart.setSessionToken(sessionToken);
		return cartRepository.save(cart);
    }

    @Override
    public Cart updateItemInCart(Long ProductID, String sessionToken, int Quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateItemInCart'");
    }

    @Override
    public Cart deleteItemFromCart(Long ProductID, String sessionToken) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteItemFromCart'");
    }

    @Override
    public Cart addToExistingCart(Long ProductID, String sessionToken, int Quantity) {
        Cart cart = cartRepository.findBySessionToken(sessionToken);
		Product p = productService.findByID(ProductID);
		Boolean productDoesExistInTheCart = false;
		if (cart != null) {
		    Set<CartItem> items = cart.getCartItems();
			for (CartItem item : items) {
				if (item.getProducts().equals(p)) {
					productDoesExistInTheCart = true;
					item.setQuantity(item.getQuantity() + Quantity);
					cart.setCartItems(items);
					return cartRepository.saveAndFlush(cart);  
				}
				
			}
		}
		if(!productDoesExistInTheCart && (cart != null))
		{
			CartItem cartItem1 = new CartItem();
			cartItem1.setQuantity(Quantity);
			cartItem1.setProducts(p);
			cart.getCartItems().add(cartItem1);
			return cartRepository.saveAndFlush(cart);
		}
		
		return this.addItemToCart(ProductID, sessionToken, Quantity);
    }

   

}
