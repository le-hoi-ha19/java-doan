package com.example.fashion.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "carts")
	private Set<CartItem> cartItems;
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;

	public Long getCartID() {
		return this.CartID;
	}

	public void setCartID(Long CartID) {
		this.CartID = CartID;
	}

	public Integer getTotalsItem() {
		return this.TotalsItem;
	}

	public void setTotalsItem(Integer TotalsItem) {
		this.TotalsItem = TotalsItem;
	}

	public Double getTotalsPrice() {
		return this.TotalsPrice;
	}

	public void setTotalsPrice(Double TotalsPrice) {
		this.TotalsPrice = TotalsPrice;
	}

	public Set<CartItem> getCartItems() {
		return this.cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Cart(Long CartID, Integer TotalsItem, Double TotalsPrice, Set<CartItem> cartItems, User user) {
		super();
		this.CartID = CartID;
		this.TotalsItem = TotalsItem;
		this.TotalsPrice = TotalsPrice;
		this.cartItems = cartItems;
		this.user = user;
	}

	public Cart() {

	}

}
