package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fashion.models.Order;
import com.example.fashion.models.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // @Query(value = "SELECT o FROM Order o WHERE o.OrderStatus = ''")
    Order findByUser(User user);

    @Query(value = "SELECT COALESCE(SUM(o.TotalsPrice), 0) FROM Order o WHERE o.OrderStatus = 'Giao hàng thành công'")
    long countTotalPrice();

}
