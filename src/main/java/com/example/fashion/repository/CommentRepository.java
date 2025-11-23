package com.example.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fashion.models.Comment;
import com.example.fashion.models.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM comments WHERE productid = :ProductID ORDER BY create_date DESC", nativeQuery = true)
    List<Comment> findCommentsByProductId(Long ProductID);

    @Query(value = "SELECT c FROM Comment c ORDER BY c.id DESC")
    List<Comment> findAllOrderByIdDesc();
}
