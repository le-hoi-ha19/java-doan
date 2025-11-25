package com.example.fashion.controller.admin;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

public abstract class BaseAdminController {
    
    @ModelAttribute
    public void addCurrentUri(HttpServletRequest request, Model model) {
        model.addAttribute("currentUri", request.getRequestURI());
    }
}
