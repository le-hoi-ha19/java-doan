package com.example.fashion.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String save(@ModelAttribute("commentForm") Comment comment, 
                       BindingResult bindingResult, 
                       RedirectAttributes redirectAttributes,
                       HttpServletRequest request,
                       @RequestParam("ProductID") Long ProductID) {
        
        // Validate form
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng điền đầy đủ thông tin.");
            return "redirect:" + request.getHeader("Referer");
        }

        // Lấy sản phẩm theo ID
        Product product = this.productService.findByID(ProductID);

        if (product == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy sản phẩm. Vui lòng thử lại.");
            return "redirect:" + request.getHeader("Referer");
        }

        // Validate rating
        if (comment.getRating() == null || comment.getRating() < 1 || comment.getRating() > 5) {
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng chọn điểm đánh giá từ 1-5 sao.");
            return "redirect:" + request.getHeader("Referer");
        }

        // Validate comment text
        if (comment.getComment() == null || comment.getComment().trim().length() < 10) {
            redirectAttributes.addFlashAttribute("errorMessage", "Đánh giá phải có ít nhất 10 ký tự.");
            return "redirect:" + request.getHeader("Referer");
        }

        // Thiết lập sản phẩm cho bình luận
        comment.setProduct(product);
        comment.setCreateDate(LocalDateTime.now()); 

        // Tạo bình luận và xử lý kết quả
        if (this.commentService.create(comment)) {
            redirectAttributes.addFlashAttribute("successMessage", "✅ Cảm ơn bạn đã đánh giá! Đánh giá của bạn đã được gửi thành công.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "❌ Có lỗi xảy ra khi gửi đánh giá. Vui lòng thử lại sau.");
        }

        // Chuyển hướng về trang trước đó
        return "redirect:" + request.getHeader("Referer");
    }

}
