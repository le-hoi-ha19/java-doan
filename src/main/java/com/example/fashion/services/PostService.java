package com.example.fashion.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.fashion.models.Post;
import com.example.fashion.models.Product;

public interface PostService {
    List<Post> getAll();

    Boolean create(Post post);

	Post findByID(Long PostID);

	Boolean update(Post post);

	Boolean delete(Long PostID);

	Page<Post> getAll(Long pageNo);

	List<Post> list3Post();

}
