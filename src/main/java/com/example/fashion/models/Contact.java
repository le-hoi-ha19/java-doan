package com.example.fashion.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.websocket.ClientEndpoint;

@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @Column(name = "ContactID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactID;
    @Column(name = "email")
    private String email;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "message")
    private String message;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "subject")
    private String subject;

    public Long getContactID() {
        return this.contactID;
    }

    public void setContactID(Long contactID) {
        this.contactID = contactID;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Contact(Long contactID, String email, String fullname, String message, String telephone, String subject) {
        super();
        this.contactID = contactID;
        this.email = email;
        this.fullname = fullname;
        this.message = message;
        this.telephone = telephone;
        this.subject = subject;
    }

    private Contact() {

    }

}
