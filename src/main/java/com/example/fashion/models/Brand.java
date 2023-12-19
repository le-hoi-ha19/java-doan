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

    public Brand() {

    }

    public Brand(Long BrandID, String BrandName, String Logo, Set<Product> products) {
        super();
        this.BrandID = BrandID;
        this.BrandName = BrandName;
        this.Logo = Logo;
        this.products = products;
    }

    public Long getBrandID() {
        return BrandID;
    }

    public void setBrandID(Long BrandID) {
        this.BrandID = BrandID;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String Logo) {
        this.Logo = Logo;
    }

    public Set<Product> getProducts(){
    return products;
    }

    public void setProducts(Set<Product> products){
    this.products = products;
    }

}
