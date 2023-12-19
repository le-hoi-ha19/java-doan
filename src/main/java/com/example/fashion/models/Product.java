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
	@Column(name = "Images")
	private String Images;
	@Column(name = "Description", length = 4000)
	private String Description;
	@OneToMany(mappedBy= "products")
	private Set<OrderDetail> orderDetails;
	@OneToMany(mappedBy= "products")
	private Set<CartItem> cartItems;
	@ManyToOne
	@JoinColumn(name = "CatID", referencedColumnName = "CatID")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "BrandID", referencedColumnName = "BrandID")
	private Brand brand;
	

	public Product() {

	}

	public Product(Long ProductID, String ProductName, Double Price, String Images,
			String Description, Category category, Brand brand, Double SalePrice, Integer Quantity, Set<OrderDetail> orderDetails) {
		super();
		this.ProductID = ProductID;
		this.ProductName = ProductName;
		this.Price = Price;
		this.Images = Images;
		this.Description = Description;
		this.category = category;
		this.brand = brand;
		this.SalePrice = SalePrice;
		this.Quantity = Quantity;
		this.orderDetails = orderDetails;
	}

	public Long getProductID() {
		return ProductID;
	}

	public void setProductID(Long ProductID) {
		this.ProductID = ProductID;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double Price) {
		this.Price = Price;
	}

	public String getImages() {
		return Images;
	}

	public void setImages(String Images) {
		this.Images = Images;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	public Double getSalePrice() {
		return SalePrice;
	}

	public void setSalePrice(Double SalePrice) {
		this.SalePrice = SalePrice;
	}

	public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer Quantity) {
		this.Quantity = Quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
