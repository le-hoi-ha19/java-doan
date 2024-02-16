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
    public Boolean update(Long ProductID, Integer Quantity, User user) {
        try {
            // Tìm kiếm giỏ hàng của người dùng
            Cart cart = cartRepository.findByUser(user);

            // Lấy danh sách các CartItem của giỏ hàng của người dùng
            Set<CartItem> cartItems = cart.getCartItems();

            // Duyệt qua từng CartItem để cập nhật số lượng và tổng giá tiền
            for (CartItem cartItem : cartItems) {
                if (cartItem.getProducts().getProductID().equals(ProductID)) {
                    // Nếu tìm thấy CartItem có ProductID tương ứng, cập nhật thông tin
                    Product product = cartItem.getProducts();
                    cartItem.setQuantity(Quantity);
                    double totalItemPrice = (product.getPrice() - product.getSalePrice()) * Quantity;
                    cartItem.setTotalsPrice(totalItemPrice);
                    cartItemRepository.save(cartItem);
                }
            }

            // Sau khi cập nhật toàn bộ CartItem, cập nhật lại tổng số lượng và tổng giá
            // tiền cho giỏ hàng
            int totalQuantity = cartItemRepository.sumQuantityByCart(cart.getCartID());
            double totalPrice = cartItemRepository.sumTotalPriceByCart(cart.getCartID());
            cart.setTotalsPrice(totalPrice);
            cart.setTotalsItem(totalQuantity);
            cartRepository.save(cart);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Long ProductID, User user) {
        try {
            Product product = productService.findByID(ProductID);
            Cart newCart = cartRepository.findByUser(user);
            CartItem cartItem = cartItemRepository.findByProduct(ProductID);
            this.cartItemRepository.delete(cartItem);

            // Kiểm tra xem còn bao nhiêu CartItem trong Cart
            int totalQuantity = this.cartItemRepository.sumQuantityByCart(newCart.getCartID());
            if (totalQuantity == 0) {
                // Nếu không còn CartItem nào, xóa luôn Cart
                this.cartRepository.delete(newCart);
                return true;
            }

            // Nếu còn CartItem, cập nhật lại thông tin của Cart
            double totalPrice = this.cartItemRepository.sumTotalPriceByCart(newCart.getCartID());
            newCart.setCartItems(cartItemRepository.findByCart(newCart));
            newCart.setTotalsPrice(totalPrice);
            newCart.setTotalsItem(totalQuantity);
            this.cartRepository.save(newCart);

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
            Cart newCart = cartRepository.findByUser(user);

            if (newCart == null) {
                newCart = new Cart();
                newCart.setUser(user);
                this.cartRepository.save(newCart);
            }

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

    @Override
    public List<CartItem> findByUser(Long id) {
        return this.cartItemRepository.findByUser(id);
    }

}
