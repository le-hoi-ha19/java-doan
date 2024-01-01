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
	@OneToMany(mappedBy = "carts")
	private Set<CartItem> cartItems;
	private String sessionToken;

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
		Double sum = 0.0;
		for (CartItem item : this.cartItems) {
			sum = sum + item.getProducts().getPrice() * item.getQuantity();
		}
		return sum;
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

	public String getSessionToken() {
		return this.sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CartID == null) ? 0 : CartID.hashCode());
		result = prime * result + ((cartItems == null) ? 0 : cartItems.hashCode());
		result = prime * result + ((sessionToken == null) ? 0 : sessionToken.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (CartID == null) {
			if (other.CartID != null)
				return false;
		} else if (!CartID.equals(other.CartID))
			return false;
		if (cartItems == null) {
			if (other.cartItems != null)
				return false;
		} else if (!cartItems.equals(other.cartItems))
			return false;
		if (sessionToken == null) {
			if (other.sessionToken != null)
				return false;
		} else if (!sessionToken.equals(other.sessionToken))
			return false;
		return true;
	}

	public Cart(Long CartID, Integer TotalsItem, Double TotalsPrice, Set<CartItem> cartItems, String sessionToken) {
		super();
		this.CartID = CartID;
		this.TotalsItem = TotalsItem;
		this.TotalsPrice = TotalsPrice;
		this.cartItems = cartItems;
		this.sessionToken = sessionToken;
	}

	public Cart() {

	}

}
