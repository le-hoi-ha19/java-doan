package com.example.fashion.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = ("OrderDetail"))
public class OrderDetail {
    @Id
    @Column(name = "OD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OD_ID;
    @Column(name = "Quantity")
    private Integer Quantity;
    @Column(name = "TotalPrice")
    private Double TotalPrice;
    @Column(name = "Price")
    private Double Price;
    @ManyToOne
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID")
    private Product products;
    @ManyToOne
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID")
    private Order orders;

    public OrderDetail() {

    }

    public OrderDetail(Long OD_ID, Integer Quantity, Double TotalPrice, Double Price, Product products, Order orders) {
        super();
        this.OD_ID = OD_ID;
        this.Quantity = Quantity;
        this.TotalPrice = TotalPrice;
        this.Price = Price;
        this.products = products;
        this.orders = orders;
    }

    public Long getOD_ID() {
        return OD_ID;
    }

    public void setOD_ID(Long OD_ID) {
        this.OD_ID = OD_ID;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer Quantity) {
        this.Quantity = Quantity;
    }

    public Double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(Double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public Product getProduct() {
        return products;
    }

    public void setProduct(Product products) {
        this.products = products;
    }

    public Order getOrder() {
        return orders;
    }

    public void setOrder(Order orders) {
        this.orders = orders;
    }
}
