package com.example.fashion.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Carts")
public class Cart {
    @Id
    @Column(name = "CartID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CartID;
    @Column(name = "TotalsItem")
    private Integer TotalsItem;
    @Column(name = "TotalsPrice")
    private Double TotalsPrice;
    @OneToMany(mappedBy= "carts")
	private Set<CartItem> cartItems;
    
    public Cart() {
    	
    }

	public Cart(Long cartID, Integer totalsItem, Double totalsPrice, Set<CartItem> cartItems) {
		super();
		CartID = cartID;
		TotalsItem = totalsItem;
		TotalsPrice = totalsPrice;
		this.cartItems = cartItems;
	}

	public Long getCartID() {
		return CartID;
	}

	public void setCartID(Long cartID) {
		CartID = cartID;
	}

	public Integer getTotalsItem() {
		return TotalsItem;
	}

	public void setTotalsItem(Integer totalsItem) {
		TotalsItem = totalsItem;
	}

	public Double getTotalsPrice() {
		return TotalsPrice;
	}

	public void setTotalsPrice(Double totalsPrice) {
		TotalsPrice = totalsPrice;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
    
}
