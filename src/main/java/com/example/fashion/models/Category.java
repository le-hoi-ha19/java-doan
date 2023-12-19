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
@Table(name = "Categories")
public class Category {
	@Id
	@Column(name = "CatID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer CatID;
	@Column(name = "CatName")
	private String CatName;

	@OneToMany(mappedBy = "category")
	private Set<Product> products;

	@OneToMany(mappedBy = "category")
	private Set<Post> posts;

	public Integer getCatID() {
		return this.CatID;
	}

	public void setCatID(Integer CatID) {
		this.CatID = CatID;
	}

	public String getCatName() {
		return this.CatName;
	}

	public void setCatName(String CatName) {
		this.CatName = CatName;
	}

	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Set<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Category(Integer CatID, String CatName, Set<Product> products, Set<Post> posts) {
		super();
		this.CatID = CatID;
		this.CatName = CatName;
		this.products = products;
		this.posts = posts;
	}

	public Category() {
		// TODO Auto-generated constructor stub
	}

}