package com.example.fashion.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.fashion.models.Brand;
import com.example.fashion.models.Category;
import com.example.fashion.models.Post;
import com.example.fashion.services.BrandService;
import com.example.fashion.services.CategoryService;
import com.example.fashion.services.PostService;
import com.example.fashion.services.StorageService;
import com.example.fashion.utils.SlugUtils;

@Controller
@RequestMapping("/admin")
public class PostController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public String index(Model model) {
        List<Post> listpost = this.postService.getAll();
        model.addAttribute("listpost", listpost);
        return "/admin/post/index";
    }

    @GetMapping("/add-post")
    public String add(Model model) {

        Post post = new Post();
        model.addAttribute("post", post);
        return "admin/post/add";
    }

    @PostMapping("/add-post")
    public String save(@ModelAttribute("post") Post post, BindingResult bindingResult,
            @RequestParam("Thumnail") MultipartFile Thumnail,
            Model model) {

        if (post.getTitle() == null || post.getTitle().trim().isEmpty() || post.getDescription() == null
                || post.getCreatedDate() == null
                || post.getContents() == null) {
            model.addAttribute("error", "Vui lòng điền đầy đủ thông tin bắt buộc");
            return "admin/post/add";
        }

        try {
            this.storageService.store(Thumnail);
            String thumbnail = Thumnail.getOriginalFilename();
            post.setThumnail(thumbnail);
            post.setTitle(post.getTitle()); // Gán Title
            post.setSlug(SlugUtils.createSlug(post.getTitle()));
            post.setContents(post.getContents()); // Gán Contents
            post.setDescription(post.getDescription()); // Gán Description
            post.setCreatedDate(post.getCreatedDate()); // Gán CreatedDate

            if (this.postService.create(post)) {
                return "redirect:/admin/post";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "admin/post/add";
    }

    @GetMapping("/edit-post/{PostID}")
    public String edit(@PathVariable("PostID") Long postId, Model model) {
        try {
            // Lấy bài viết từ service
            Post post = postService.findByID(postId);

            // Kiểm tra nếu bài viết không tồn tại
            if (post == null) {
                model.addAttribute("error", "Bài viết không tồn tại");
                return "redirect:/admin/post"; // Quay lại danh sách bài viết
            }

            // Thêm bài viết vào model để hiển thị trong form
            model.addAttribute("post", post);

            // Trả về view của trang edit
            return "admin/post/edit";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Lỗi xảy ra khi lấy bài viết");
            return "redirect:/admin/post";
        }
    }

    @PostMapping("/edit-post/{id}")
    public String edit(@PathVariable("id") Long id,
            @ModelAttribute("post") Post post, BindingResult bindingResult,
            @RequestParam("Thumnail") MultipartFile Thumnail,
            Model model) {

        // Kiểm tra các trường bắt buộc
        if (post.getTitle() == null || post.getTitle().trim().isEmpty() || post.getDescription() == null
                || post.getCreatedDate() == null || post.getContents() == null) {
            model.addAttribute("error", "Vui lòng điền đầy đủ thông tin bắt buộc");
            return "admin/post/edit";
        }

        try {
            // Tìm bài viết cần sửa theo ID
            Post existingPost = postService.findByID(id);
            if (existingPost == null) {
                model.addAttribute("error", "Bài viết không tồn tại");
                return "admin/post/edit";
            }

            // Cập nhật các trường cho bài viết
            existingPost.setTitle(post.getTitle());
            existingPost.setContents(post.getContents());
            existingPost.setDescription(post.getDescription());
            existingPost.setCreatedDate(post.getCreatedDate());

            // Nếu có ảnh đại diện mới, lưu ảnh
            if (Thumnail != null && !Thumnail.isEmpty()) {
                this.storageService.store(Thumnail);
                String ThumnailFile = Thumnail.getOriginalFilename();
                existingPost.setThumnail(ThumnailFile);
            }
            // Cập nhật bài viết
            if (postService.update(existingPost)) {
                return "redirect:/admin/post";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "admin/post/edit";
    }

    @GetMapping("/delete-post/{PostID}")
    public String delete(@PathVariable("PostID") Long PostID) {
        if (this.postService.delete(PostID)) {
            return "redirect:/admin/post";
        } else {
            return "redirect:/admin/post";
        }

    }
}
