package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fashion.models.Order;
import com.example.fashion.models.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUser(User user);
    
}
