package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fashion.models.Order;
import com.example.fashion.models.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long>{

    @Query("SELECT od from OrderDetail od WHERE od.products.id =:productId")
    OrderDetail findByProductAndOrder(Long productId);
    
}
