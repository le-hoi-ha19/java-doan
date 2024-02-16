package com.example.fashion.services;
import java.util.List;

import com.example.fashion.models.Cart;
import com.example.fashion.models.CartItem;
import com.example.fashion.models.Order;
import com.example.fashion.models.OrderDetail;
import com.example.fashion.models.User;

public interface OrderService {
    List<Order> getAll();

    Boolean create(Cart cart, CartItem cartItem);

    long countTotalPrice();

    Order findByUser(User user);

    Order findByID(Long OrderID);

    Boolean update(Order order);
}
