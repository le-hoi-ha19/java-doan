package com.example.fashion.services.impl;

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

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Boolean addItemToCart(Product product, Integer quantity) {
        try {
            // Kiểm tra xem có giỏ hàng nào tồn tại không
            Cart cart = cartRepository.findCartByUserIdIsNull();

            if (cart == null) {
                // Nếu không có, tạo một giỏ hàng mới
                cart = new Cart();
                cartRepository.save(cart);
            }

            // Tạo một cart item và thêm vào giỏ hàng
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);

            // Thực hiện tính số lượng và giá tiền của sản phẩm
            cartItem.setQuantity(quantity);

            // Tính giá tiền cho sản phẩm (giả sử có giá tiền trong đối tượng Product)
            Double totalPrice = product.getPrice() * quantity;
            cartItem.setTotalPrice(totalPrice);

            // Lưu cart item
            cartItemRepository.save(cartItem);

            // Cập nhật tổng số lượng và tổng giá tiền của giỏ hàng
            cart.setTotalsItem(cart.getTotalsItem() + quantity);
            cart.setTotalsPrice(cart.getTotalsPrice() + totalPrice);

            // Lưu giỏ hàng
            cartRepository.save(cart);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateItemInCart(Product product, Integer Quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateItemInCart'");
    }

    @Override
    public Boolean deleteItemFromCart(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteItemFromCart'");
    }

}
