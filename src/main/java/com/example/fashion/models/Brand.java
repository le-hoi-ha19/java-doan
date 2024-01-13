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
@Table(name = "Brands")
public class Brand {
    @Id
    @Column(name = "BrandID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BrandID;
    @Column(name = "BrandName")
    private String BrandName;
    @Column(name = "Logo")
    private String Logo;
    @OneToMany(mappedBy = "brand")
    private Set<Product> products;
    @OneToMany(mappedBy = "brand")
	private Set<Post> posts;

    public Long getBrandID() {
        return this.BrandID;
    }

    public void setBrandID(Long BrandID) {
        this.BrandID = BrandID;
    }

    public String getBrandName() {
        return this.BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public String getLogo() {
        return this.Logo;
    }

    public void setLogo(String Logo) {
        this.Logo = Logo;
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

    public Brand(Long BrandID, String BrandName, String Logo, Set<Product> products, Set<Post> posts) {
        super();
        this.BrandID = BrandID;
        this.BrandName = BrandName;
        this.Logo = Logo;
        this.products = products;
        this.posts = posts;
    }

    public Brand() {

    }

    

}
