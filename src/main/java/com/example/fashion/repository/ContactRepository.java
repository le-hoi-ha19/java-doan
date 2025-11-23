package com.example.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fashion.models.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{
    
    @Query(value = "SELECT c FROM Contact c ORDER BY c.id DESC")
    List<Contact> findAllOrderByIdDesc();
}
