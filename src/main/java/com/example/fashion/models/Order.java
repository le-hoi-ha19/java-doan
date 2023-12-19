package com.example.fashion.models;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = ("Orders"))
public class Order {
    @Id
    @Column(name = "OrderID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderID;
    @Column(name = "OrderDate")
    private Date OrderDate;
    @Column(name = "DeliveryDate")
    private Date DeliveryDate;
    @Column(name = "TotalPrice")
    private Double TotalPrice;
    @Column(name = "ShippingFee")
    private Double ShippingFee;
    @Column(name = "OrderStatus")
    private String OrderStatus;
    @Column(name = "Notes")
    private String Notes;
    @OneToMany(mappedBy= "orders")
	private Set<OrderDetail> orderDetails;

    public Order() {

    }

    public Order(Long OrderID, Date OrderDate, Date DeliveryDate, Double TotalPrice, Double ShippingFee,
            String OrderStatus, String Notes, Set<OrderDetail> orderDetails) {
        super();
        this.OrderID = OrderID;
        this.OrderDate = OrderDate;
        this.DeliveryDate = DeliveryDate;
        this.TotalPrice = TotalPrice;
        this.ShippingFee = ShippingFee;
        this.OrderStatus = OrderStatus;
        this.Notes = Notes;
        this.orderDetails = orderDetails;
    }

    public Long OrderID() {
        return OrderID;
    }

    public void setOD_ID(Long OrderID) {
        this.OrderID = OrderID;
    }

    public Date OrderDate() {
        return OrderDate;
    }

    public void OrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }

    public Date DeliveryDate() {
        return DeliveryDate;
    }

    public void DeliveryDate(Date DeliveryDate) {
        this.DeliveryDate = DeliveryDate;
    }

    public Double TotalPrice() {
        return TotalPrice;
    }

    public void TotalPrice(Double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public Double ShippingFee() {
        return ShippingFee;
    }

    public void ShippingFee(Double ShippingFee) {
        this.ShippingFee = ShippingFee;
    }

    public String OrderStatus() {
        return OrderStatus;
    }

    public void OrderStatus(String OrderStatus) {
        this.OrderStatus = OrderStatus;
    }

    public String Notes() {
        return Notes;
    }

    public void Notes(String Notes) {
        this.Notes = Notes;
    }

    public Set<OrderDetail>	getOrderDetails(){
		return orderDetails;
	}
	
	public void setOrderDetails(Set<OrderDetail> orderDetails){
		this.orderDetails = orderDetails;
	}
}
