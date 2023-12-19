package com.example.fashion.services;

import java.util.List;

import com.example.fashion.models.Post;

public interface PostService {
    List<Post> getAll();

    Boolean create(Post post);

	Post findByID(Long PostID);

	Boolean update(Post post);

	Boolean delete(Long PostID);
}
