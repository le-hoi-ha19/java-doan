package com.example.fashion.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fashion.models.Comment;
import com.example.fashion.repository.CommentRepository;
import com.example.fashion.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getAll() {
        return this.commentRepository.findAll();
    }

    @Override
    public Boolean create(Comment comment) {
        try {
            this.commentRepository.save(comment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Comment findByID(Long id) {
        return this.commentRepository.findById(id).get();
    }

    @Override
    public Boolean update(Comment comment) {
        try {
            this.commentRepository.save(comment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        try {
            this.commentRepository.delete(findByID(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // @Override
    // public List<Comment> findbyidPost() {
    //     return this.commentRepository.findbyidPost();
    // }
    
}
