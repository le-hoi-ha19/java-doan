package com.example.fashion.services;

import java.util.List;

import com.example.fashion.models.Contact;

public interface ContactService {
    List<Contact> getAll();

	Boolean create(Contact contact);

	Contact findByID(Long contactID);

	Boolean update(Contact contact);

	Boolean delete(Long contactID);
}
