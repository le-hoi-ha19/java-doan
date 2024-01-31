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

    @Override
    @Transactional
    public Boolean create(Long ProductID, Integer Quantity, User user) {
        try {
            Product product = productService.findByID(ProductID);

            // Tạo giỏ hàng mới
            Cart newCart = new Cart();
            newCart.setCartID((1L));
            newCart.setUser(user);
            this.cartRepository.save(newCart);

            CartItem cartItem = cartItemRepository.findByProduct(ProductID);

            if (cartItem != null) {
                // Nếu có, cập nhật số lượng và tổng giá
                double totalsPrice = (product.getPrice() - product.getSalePrice()) * Quantity;
                cartItem.setQuantity(cartItem.getQuantity() + Quantity);
                cartItem.setTotalsPrice(cartItem.getTotalsPrice() + totalsPrice);
                cartItemRepository.save(cartItem);
            } else {
                // Nếu không có, tạo một CartItem mới và thêm vào giỏ hàng
                double totalsPrice = (product.getPrice() - product.getSalePrice()) * Quantity;
    
                CartItem newCartItem = new CartItem();
                newCartItem.setCarts(newCart);
                newCartItem.setProducts(product);
                newCartItem.setQuantity(Quantity);
                newCartItem.setTotalsPrice(totalsPrice);
    
                cartItemRepository.save(newCartItem);
            }

            // Cập nhật thông tin giỏ hàng mới
            int totalQuantity = this.cartItemRepository.sumQuantityByCart(newCart.getCartID());
            double totalprice = this.cartItemRepository.sumTotalPriceByCart(newCart.getCartID());
            newCart.setTotalsPrice(totalprice);
            newCart.setTotalsItem(totalQuantity);
            this.cartRepository.save(newCart);

            // Trả về true để chỉ ra rằng quá trình tạo hoặc cập nhật thành công
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
