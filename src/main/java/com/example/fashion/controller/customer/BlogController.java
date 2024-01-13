package com.example.fashion.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fashion.models.Brand;
import com.example.fashion.models.Category;
import com.example.fashion.models.Comment;
import com.example.fashion.models.Post;
import com.example.fashion.models.Product;
import com.example.fashion.services.BrandService;
import com.example.fashion.services.CategoryService;
import com.example.fashion.services.CommentService;
import com.example.fashion.services.ContactService;
import com.example.fashion.services.PostService;
import com.example.fashion.services.ProductService;
import com.example.fashion.services.UserService;

@Controller
public class BlogController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @GetMapping("/blog")
    public String index(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Long pageNo) {
        Page<Post> listPost = this.postService.getAll(pageNo);

        if (listPost != null) {
            model.addAttribute("totalPage", listPost.getTotalPages());
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("listPost", listPost);
        }

        List<Brand> listBra = this.brandService.getAll();
        model.addAttribute("listBra", listBra);
        List<Comment> comments = this.commentService.getAll();
        model.addAttribute("comments", comments);
        return "blog/index";
    }

    @GetMapping("/blog-detail/{PostID}")
    public String detail(Model model, @PathVariable("PostID") Long PostID) {
        Post post = this.postService.findByID(PostID);
        model.addAttribute("Post", post);
        List<Category> categories = this.categoryService.getAll();
        if (categories != null) {
            model.addAttribute("categories", categories);
        }

        List<Brand> listBra = this.brandService.getAll();
        if (listBra != null) {
            model.addAttribute("listBra", listBra);
        }

        List<Post> listPost = this.postService.list3Post();
        if (listPost != null) {
            model.addAttribute("listPost", listPost);
        }

        return "blog/detail";
    }
}
