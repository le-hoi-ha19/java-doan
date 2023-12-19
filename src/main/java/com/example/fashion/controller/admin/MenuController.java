package com.example.fashion.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/menu")
public class MenuController {
    @GetMapping
    public String index() {
        return "/admin/menu/index";
    }
}
