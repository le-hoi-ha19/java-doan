package com.example.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fashion.models.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
     @Query(value = "SELECT p FROM Post p ORDER BY p.PostID DESC LIMIT 3")
    List<Post> list3Post();

    @Query(value = "SELECT p FROM Post p ORDER BY p.PostID DESC")
    List<Post> findAllOrderByIdDesc();
}
