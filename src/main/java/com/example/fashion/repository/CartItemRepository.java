package com.example.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fashion.models.Cart;
import com.example.fashion.models.CartItem;
import com.example.fashion.models.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("SELECT COALESCE(SUM(ci.Quantity), 0) FROM CartItem ci WHERE ci.carts.id = :cartId")
    int sumQuantityByCart(@Param("cartId") Long cartId);

    @Query("SELECT COALESCE(SUM(ci.TotalsPrice), 0) FROM CartItem ci WHERE ci.carts.id = :cartId")
    double sumTotalPriceByCart(@Param("cartId") Long cartID);

    @Query("SELECT ci FROM CartItem ci WHERE ci.products.id = :productId")
    CartItem findByProduct(@Param("productId") Long productId);

    @Query("SELECT ci FROM CartItem ci JOIN ci.carts c WHERE c.user.id = :userId")
    List<CartItem> findByUser(@Param("userId") Long userId);

}
