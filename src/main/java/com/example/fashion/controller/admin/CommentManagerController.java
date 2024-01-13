package com.example.fashion.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fashion.models.Comment;
import com.example.fashion.services.CommentService;


@Controller
@RequestMapping("/admin")
public class CommentManagerController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comment")
    public String index(Model model) {
        List<Comment> list = this.commentService.getAll();
        model.addAttribute("list", list);
        return "admin/comment/index";
    }

    @GetMapping("/delete-comment/{id}")
    public String delete(@PathVariable("id") Long id) {
        if (this.commentService.delete(id)) {
            return "redirect:/admin/comment";
        } else {
            return "redirect:/admin/comment";
        }

    }
}

