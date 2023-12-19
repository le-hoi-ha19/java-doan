// package com.example.fashion.models;

// import java.util.Set;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "ProductImages")
// public class ProductImages {
// 	@Id
// 	@Column(name = "imgID")
// 	@GeneratedValue(strategy = GenerationType.IDENTITY)
// 	private Long imgID;
// 	@Column(name = "Images")
// 	private String Images;
// 	@OneToMany(mappedBy = "ProductImages")
// 	private Set<Product> products;

// 	public ProductImages(Long imgID, String images, Set<Product> products) {
// 		super();
// 		this.imgID = imgID;
// 		Images = images;
// 		this.products = products;
// 	}

// 	public Long getImgID() {
// 		return imgID;
// 	}

// 	public void setImgID(Long imgID) {
// 		this.imgID = imgID;
// 	}

// 	public String getImages() {
// 		return Images;
// 	}

// 	public void setImages(String images) {
// 		Images = images;
// 	}

// 	public Set<Product> getProducts() {
// 		return products;
// 	}

// 	public void setProducts(Set<Product> products) {
// 		this.products = products;
// 	}
// }
