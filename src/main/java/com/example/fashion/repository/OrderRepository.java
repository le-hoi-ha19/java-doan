package com.example.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fashion.models.Order;
import com.example.fashion.models.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT o FROM Order o WHERE o.user = :user ORDER BY o.OrderID DESC")
    List<Order> findByUserOrderByOrderIDDesc(@Param("user") User user);

    @Query(value = "SELECT COALESCE(SUM(o.TotalsPrice), 0) FROM Order o WHERE o.OrderStatus = 'Giao hàng thành công'")
    long countTotalPrice();

    @Query(value = "SELECT o FROM Order o ORDER BY o.OrderID DESC")
    List<Order> findAllOrderByIdDesc();

    @Query(value = "SELECT COUNT(o) FROM Order o")
    long countTotalOrders();

    @Query(value = "SELECT COUNT(o) FROM Order o WHERE o.OrderStatus = 'Chờ xử lý'")
    long countPendingOrders();

    @Query(value = "SELECT COUNT(o) FROM Order o WHERE o.OrderStatus = 'Đang giao hàng'")
    long countShippingOrders();

    @Query(value = "SELECT COUNT(o) FROM Order o WHERE o.OrderStatus = 'Giao hàng thành công'")
    long countCompletedOrders();

    @Query(value = "SELECT COUNT(o) FROM Order o WHERE o.OrderStatus = 'Đã hủy'")
    long countCancelledOrders();

}
