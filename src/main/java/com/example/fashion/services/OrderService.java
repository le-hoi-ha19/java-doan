package com.example.fashion.services;
import java.util.List;

import com.example.fashion.models.Order;
import com.example.fashion.models.User;

public interface OrderService {
    List<Order> getAll();

    Boolean create(Long ProductID, Integer Quantity, User user);

}
