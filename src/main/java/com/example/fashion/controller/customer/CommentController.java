package com.example.fashion.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fashion.models.Comment;
import com.example.fashion.models.Contact;
import com.example.fashion.models.Post;
import com.example.fashion.models.Product;
import com.example.fashion.services.CommentService;
import com.example.fashion.services.PostService;
import com.example.fashion.services.ProductService;
import java.time.LocalDateTime; // Nếu sử dụng LocalDateTime
import java.util.Date; // Nếu sử dụng Date
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PostService postService;

    @PostMapping("/add-comment")
    public String save(@ModelAttribute("comment") Comment comment, BindingResult bindingResult, Model model,
            HttpServletRequest request,
            @RequestParam("ProductID") Long ProductID) {
        if (bindingResult.hasErrors()) {
            // Nếu có lỗi hợp lệ, trả về trang trước đó với thông báo lỗi
            return "redirect:" + request.getHeader("Referer");
        }

        // Lấy sản phẩm theo ID
        Product product = this.productService.findByID(ProductID);

        if (product == null) {
            // Nếu không tìm thấy sản phẩm, trả về trang trước đó với thông báo lỗi
            model.addAttribute("errorMessage", "Không tìm thấy sản phẩm. Vui lòng thử lại.");
            return "redirect:" + request.getHeader("Referer");
        }

        // Thiết lập sản phẩm cho bình luận
        comment.setProduct(product);
        comment.setCreateDate(LocalDateTime.now()); 

        // Tạo bình luận và xử lý kết quả
        if (this.commentService.create(comment)) {
            
            model.addAttribute("successMessage", "Bình luận của bạn đã được gửi thành công!");
        } else {
            // Thêm thông báo lỗi nếu không thể lưu bình luận
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi gửi bình luận. Vui lòng thử lại.");
        }

        // Chuyển hướng về trang trước đó
        return "redirect:" + request.getHeader("Referer");
    }

}
