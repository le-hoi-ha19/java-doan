package com.example.fashion.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import com.example.fashion.models.Order;
import com.example.fashion.models.OrderDetail;
import com.example.fashion.models.Product;
import com.example.fashion.models.User;
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

    @Override
    public List<Order> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Boolean create(Long ProductID, Integer Quantity, User user) {
        try {
            // Order newOrder = orderRepository.findByUser(user);

            // if (newOrder == null) {
            //     newOrder = new Order();
            //     newOrder.setUser(user);
            //     // newOrder.setOrderStatus("Chờ xử lý");
            //     // newOrder.setOrderDate(LocalDate.now());
            //     // LocalDate deliveryDate = LocalDate.now().plusDays(7);
            //     // newOrder.setDeliveryDate(deliveryDate);
            //     // newOrder.setShippingFee((double) 15000);
            //     this.orderRepository.save(newOrder);
            // }
            Order order = orderRepository.findByUser(user);
            if(order == null){
                order = new Order();
                order.setUser(UserService.findByUsername());
                this.orderRepository.save(order);
            }

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            this.orderDetailRepository.save(orderDetail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
