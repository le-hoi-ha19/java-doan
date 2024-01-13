package com.example.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fashion.models.Comment;
import com.example.fashion.models.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
   
}
