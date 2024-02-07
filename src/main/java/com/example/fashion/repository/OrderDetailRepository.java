package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fashion.models.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query("SELECT od FROM OrderDetail od WHERE od.products.id = :productId")
    OrderDetail findByProduct(@Param("productId") Long productId);

    @Query("SELECT COALESCE(SUM(od.Quantity), 0) FROM OrderDetail od WHERE od.orders.id = :orderId")
    int sumQuantityByOrder(@Param("orderId") Long orderId);

    @Query("SELECT COALESCE(SUM(od.TotalPrice), 0) FROM OrderDetail od WHERE od.orders.id = :orderId")
    int sumTotalPriceByOrder(@Param("orderId") Long orderId);
}
