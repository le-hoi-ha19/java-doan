package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fashion.models.Cart;
import com.example.fashion.models.CartItem;
import com.example.fashion.models.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
   
    Cart findByUser(User user);

    // Cart findByUserID(Long id);
    
}
