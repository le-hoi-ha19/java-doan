package com.example.fashion.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fashion.models.Post;
import com.example.fashion.models.Product;
import com.example.fashion.repository.PostRepository;
import com.example.fashion.repository.ProductRepository;
import com.example.fashion.services.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAll() {
        return this.postRepository.findAll();
    }

    @Override
    public Boolean create(Post post) {
        try {
            this.postRepository.save(post);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Post findByID(Long PostID) {
        return this.postRepository.findById(PostID).get();

    }

    @Override
    public Boolean update(Post post) {
        try {
            this.postRepository.save(post);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Long PostID) {
        try {
            this.postRepository.delete(findByID(PostID));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
