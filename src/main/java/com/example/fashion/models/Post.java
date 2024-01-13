package com.example.fashion.models;

import java.sql.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Posts")
public class Post {
    @Id
    @Column(name = "PostID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PostID;
    @Column(name = "Title")
    private String Title;
    @Column(name = "Contents", length = 100000)
    private String Contents;
    @Column(name = "Avatar")
    private String Avatar;
    @Column(name = "Img1")
    private String Img1;
    @Column(name = "Img2")
    private String Img2;
    @Column(name = "Img3")
    private String Img3;
    @Column(name = "Abstract", length = 100000)
    private String Abstract;
    @Column (name = "CreatedDate")
    private Date CreatedDate;
    @ManyToOne
    @JoinColumn(name = "BrandID", referencedColumnName = "BrandID")
    private Brand brand;

    @OneToMany(mappedBy = "post")
	private Set<Comment> comment;

    public Long getPostID() {
        return this.PostID;
    }

    public void setPostID(Long PostID) {
        this.PostID = PostID;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getContents() {
        return this.Contents;
    }

    public void setContents(String Contents) {
        this.Contents = Contents;
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

    public String getAbstract() {
        return this.Abstract;
    }

    public void setAbstract(String Abstract) {
        this.Abstract = Abstract;
    }

    public Date getCreatedDate() {
        return this.CreatedDate;
    }

    public void setCreatedDate(Date CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    public Brand getBrand() {
        return this.brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Set<Comment> getComment() {
        return this.comment;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }

    public Post(Long PostID, String Title, String Contents, String Avatar, String Img1, String Img2, String Img3, String Abstract, Date CreatedDate, Brand brand, Set<Comment> comment) {
        super();
        this.PostID = PostID;
        this.Title = Title;
        this.Contents = Contents;
        this.Avatar = Avatar;
        this.Img1 = Img1;
        this.Img2 = Img2;
        this.Img3 = Img3;
        this.Abstract = Abstract;
        this.CreatedDate = CreatedDate;
        this.brand = brand;
        this.comment = comment;
    }

    

   

    public Post() {

	}
}
