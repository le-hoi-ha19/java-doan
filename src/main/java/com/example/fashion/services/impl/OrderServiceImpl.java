package com.example.fashion.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import com.example.fashion.models.Cart;
import com.example.fashion.models.CartItem;
import com.example.fashion.models.Order;
import com.example.fashion.models.OrderDetail;
import com.example.fashion.models.Product;
import com.example.fashion.models.User;
import com.example.fashion.repository.CartItemRepository;
import com.example.fashion.repository.CartRepository;
import com.example.fashion.repository.OrderDetailRepository;
import com.example.fashion.repository.OrderRepository;
import com.example.fashion.services.OrderService;
import com.example.fashion.services.ProductService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAll();

    }

    @Override
    public Boolean create(Cart cart, CartItem cartItem) {
        try {
            // Tìm hoặc tạo mới Order cho User từ Cart
            Order order = orderRepository.findByUser(cart.getUser());
            if (order == null) {
                order = new Order();
                order.setUser(cart.getUser());
                order.setOrderStatus("Chờ xử lý");
                order.setOrderDate(LocalDate.now());
                LocalDate deliveryDate = LocalDate.now().plusDays(7);
                order.setDeliveryDate(deliveryDate);
                order.setShippingFee((double) 15000);
                order.setTotalsPrice(cart.getTotalsPrice());
                this.orderRepository.save(order);
            }

            // Lưu OrderDetail với Order đã xác định
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            double itemPrice = cartItem.getTotalsPrice() / cartItem.getQuantity();
            orderDetail.setPrice(itemPrice);
            orderDetail.setProduct(cartItem.getProducts());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setTotalPrice(cartItem.getTotalsPrice());
            this.orderDetailRepository.save(orderDetail);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public long countTotalPrice() {
        return orderRepository.countTotalPrice();
    }

    @Override
    public Order findByUser(User user) {
        return orderRepository.findByUser(user);

    }

    @Override
    public Order findByID(Long OrderID) {
        return orderRepository.findById(OrderID).get();
    }

    @Override
    public Boolean update(Order order) {
        try {
            this.orderRepository.save(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
