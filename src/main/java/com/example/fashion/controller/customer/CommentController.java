package com.example.fashion.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.fashion.models.Comment;
import com.example.fashion.models.Contact;
import com.example.fashion.services.CommentService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add-comment")
    public String save(@ModelAttribute("comment") Comment comment, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            // Nếu có lỗi hợp lệ, trả về trang thêm danh mục với thông báo lỗi
            return "redirect:" + request.getHeader("Referer");
        }
        if (this.commentService.create(comment)) {
            // Thêm thông báo thành công vào model
            model.addAttribute("successMessage", "Bình luận của bạn đã được gửi thành công!");

            // Chuyển hướng đến trang index hoặc trang khác tùy theo yêu cầu của bạn
            return "redirect:" + request.getHeader("Referer");
        } else {
            // Nếu có lỗi khi lưu tin nhắn, bạn có thể thêm thông báo lỗi vào model
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi gửi bình luận. Vui lòng thử lại.");

            return "redirect:" + request.getHeader("Referer");
        }
    }
}
