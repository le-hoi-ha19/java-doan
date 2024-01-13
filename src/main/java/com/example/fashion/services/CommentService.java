package com.example.fashion.services;

import java.util.List;

import com.example.fashion.models.Comment;

public interface CommentService {
    List<Comment> getAll();

	Boolean create(Comment comment);

	Comment findByID(Long id);

	Boolean update(Comment comment);

	Boolean delete(Long id);

	// List<Comment> findbyidPost();
}
