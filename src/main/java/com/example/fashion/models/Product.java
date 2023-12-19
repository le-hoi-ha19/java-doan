package com.example.fashion.models;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Products")
public class Product {
	@Id
	@Column(name = "ProductID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ProductID;
	@Column(name = "ProductName")
	private String ProductName;
	@Column(name = "Price")
	private Double Price;
	@Column(name = "SalePrice")
	private Double SalePrice;
	@Column(name = "Quantity")
	private Integer Quantity;
	@Column(name = "Avatar")
	private String Avatar;
	@Column(name = "Img1")
	private String Img1;
	@Column(name = "Img2")
	private String Img2;
	@Column(name = "Img3")
	private String Img3;
	@Column(name = "Description", length = 4000)
	private String Description;
	@OneToMany(mappedBy = "products")
	private Set<OrderDetail> orderDetails;
	@OneToMany(mappedBy = "products")
	private Set<CartItem> cartItems;
	@ManyToOne
	@JoinColumn(name = "CatID", referencedColumnName = "CatID")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "BrandID", referencedColumnName = "BrandID")
	private Brand brand;

	public Long getProductID() {
		return this.ProductID;
	}

	public void setProductID(Long ProductID) {
		this.ProductID = ProductID;
	}

	public String getProductName() {
		return this.ProductName;
	}

	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}

	public Double getPrice() {
		return this.Price;
	}

	public void setPrice(Double Price) {
		this.Price = Price;
	}

	public Double getSalePrice() {
		return this.SalePrice;
	}

	public void setSalePrice(Double SalePrice) {
		this.SalePrice = SalePrice;
	}

	public Integer getQuantity() {
		return this.Quantity;
	}

	public void setQuantity(Integer Quantity) {
		this.Quantity = Quantity;
	}

	public String getAvatar() {
		return this.Avatar;
	}

	public void setAvatar(String Avatar) {
		this.Avatar = Avatar;
	}

	public String getImg1() {
		return this.Img1;
	}

	public void setImg1(String Img1) {
		this.Img1 = Img1;
	}

	public String getImg2() {
		return this.Img2;
	}

	public void setImg2(String Img2) {
		this.Img2 = Img2;
	}

	public String getImg3() {
		return this.Img3;
	}

	public void setImg3(String Img3) {
		this.Img3 = Img3;
	}

	public String getDescription() {
		return this.Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	public Set<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Set<CartItem> getCartItems() {
		return this.cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Product(Long ProductID, String ProductName, Double Price, Double SalePrice, Integer Quantity, String Avatar,
			String Img1, String Img2, String Img3, String Description, Set<OrderDetail> orderDetails,
			Set<CartItem> cartItems, Category category, Brand brand) {
		super();
		this.ProductID = ProductID;
		this.ProductName = ProductName;
		this.Price = Price;
		this.SalePrice = SalePrice;
		this.Quantity = Quantity;
		this.Avatar = Avatar;
		this.Img1 = Img1;
		this.Img2 = Img2;
		this.Img3 = Img3;
		this.Description = Description;
		this.orderDetails = orderDetails;
		this.cartItems = cartItems;
		this.category = category;
		this.brand = brand;
	}

	public Product() {

	}

}
