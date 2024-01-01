package com.example.fashion.models;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CartItems")
public class CartItem {
    @Id
    @Column(name = "CI_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CI_ID;
    @Column(name = "Quantity")
    private Integer Quantity;
    @Column(name = "TotalsPrice")
    private Double TotalsPrice;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID")
    private Product products;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CartID", referencedColumnName = "CartID")
    private Cart carts;

    public Long getCI_ID() {
        return this.CI_ID;
    }

    public void setCI_ID(Long CI_ID) {
        this.CI_ID = CI_ID;
    }

    public Integer getQuantity() {
        return this.Quantity;
    }

    public void setQuantity(Integer Quantity) {
        this.Quantity = Quantity;
    }

    public Double getTotalsPrice() {
        return this.TotalsPrice;
    }

    public void setTotalsPrice(Double TotalsPrice) {
        this.TotalsPrice = TotalsPrice;
    }

    public Product getProducts() {
        return this.products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    public Cart getCarts() {
        return this.carts;
    }

    public void setCarts(Cart carts) {
        this.carts = carts;
    }

    public CartItem(Long CI_ID, Integer Quantity, Double TotalsPrice, Product products, Cart carts) {
        super();
        this.CI_ID = CI_ID;
        this.Quantity = Quantity;
        this.TotalsPrice = TotalsPrice;
        this.products = products;
        this.carts = carts;
    }

    public CartItem() {

    }

   
}
