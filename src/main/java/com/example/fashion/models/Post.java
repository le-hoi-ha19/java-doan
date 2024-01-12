package com.example.fashion.models;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Column(name = "Contents")
    private String Contents;
    @Column(name = "Avatar")
    private String Avatar;
    @Column(name = "Img1")
    private String Img1;
    @Column(name = "Img2")
    private String Img2;
    @Column(name = "Img3")
    private String Img3;
    @Column(name = "Abstract")
    private String Abstract;
    @Column (name = "CreatedDate")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date CreatedDate;
    @ManyToOne
    @JoinColumn(name = "CatID", referencedColumnName = "CatID")
    private Category category;

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

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Post(Long PostID, String Title, String Contents, String Avatar, String Img1, String Img2, String Img3, String Abstract, Date CreatedDate, Category category) {
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
        this.category = category;
    }

    public Post() {

	}
}
