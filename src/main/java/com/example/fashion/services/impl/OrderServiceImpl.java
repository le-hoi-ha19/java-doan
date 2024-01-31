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
            Product product = productService.findByID(ProductID);

            Order newOrder = new Order();
            newOrder.setUser(user);
            newOrder.setOrderID(1L);
            newOrder.setOrderStatus("Chờ xử lý"); // Set trạng thái đơn hàng là "Chờ xử lý"
            newOrder.setOrderDate(LocalDate.now()); // Set ngày đặt hàng là ngày hôm nay
            LocalDate deliveryDate = LocalDate.now().plusDays(7);
            newOrder.setDeliveryDate(deliveryDate);
            this.orderRepository.save(newOrder);

            OrderDetail orderDetail = orderDetailRepository.findByProductAndOrder(ProductID);

            OrderDetail newOD = new OrderDetail();
            newOD.setOrder(newOrder);
            newOD.setProduct(product);
            newOD.setPrice(product.getPrice());
            this.orderDetailRepository.save(newOD);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
