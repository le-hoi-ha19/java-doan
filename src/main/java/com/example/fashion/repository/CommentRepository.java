package com.example.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fashion.models.Comment;
import com.example.fashion.models.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.product.ProductID = :productId ORDER BY c.createDate DESC")
    List<Comment> findCommentsByProductId(Long productId);

    @Query(value = "SELECT c FROM Comment c ORDER BY c.id DESC")
    List<Comment> findAllOrderByIdDesc();
}
