package com.example.fashion.models;

import java.sql.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Posts")
public class Post {
    @Id
    @Column(name = "PostID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PostID;

    @Column(name = "Title")
    private String Title;

    @Column(name = "Slug", unique = true, nullable = false)
    private String Slug; // Thêm trường Slug

    @Column(name = "Description", length = 100000)
    private String Description;

    @Column(name = "Contents", length = 100000)
    private String Contents;

    @Column(name = "Thumnail")
    private String Thumnail;

    @Column(name = "CreatedDate")
    private Date CreatedDate;

    public Long getPostID() {
        return PostID;
    }

    public void setPostID(Long postID) {
        PostID = postID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }

    public String getSlug() {
        return Slug;
    }

    public void setSlug(String slug) {
        Slug = slug;
    }

    public String getThumnail() {
        return Thumnail;
    }

    public void setThumnail(String thumnail) {
        Thumnail = thumnail;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public Post(Long postID, String title, String contents, String thumnail,
            String description, Date createdDate, String slug) {
        super();
        PostID = postID;
        Title = title;
        Slug = slug;
        Contents = contents;
        Thumnail = thumnail;
        Description = description;
        CreatedDate = createdDate;
    }

    public Post() {
    }
}
