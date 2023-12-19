package com.example.fashion.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fashion.models.Contact;
import com.example.fashion.repository.ContactRepository;
import com.example.fashion.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Override
    public List<Contact> getAll() {
        return this.contactRepository.findAll();
    }

    @Override
    public Boolean create(Contact contact) {
        try {
            this.contactRepository.save(contact);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Contact findByID(Long contactID) {
        return this.contactRepository.findById(contactID).get();
    }

    @Override
    public Boolean update(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Boolean delete(Long contactID) {
        try {
            this.contactRepository.delete(findByID(contactID));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
