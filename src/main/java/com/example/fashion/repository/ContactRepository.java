package com.example.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fashion.models.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{
    
}
