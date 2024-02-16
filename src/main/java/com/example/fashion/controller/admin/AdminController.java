package com.example.fashion.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fashion.models.Product;
import com.example.fashion.services.OrderService;
import com.example.fashion.services.ProductService;
import com.example.fashion.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;
    @GetMapping
    public String index() {
        return "redirect:/admin/";
    }

    @RequestMapping("/")
    public String admin(Model model) {
        long totalProducts = this.productService.countTotalProducts();
        model.addAttribute("totalProducts", totalProducts);
        long totalUser = this.userService.countTotalUsers();
        model.addAttribute("totalUser", totalUser);
        long totalPrice = this.orderService.countTotalPrice();
        model.addAttribute("totalPrice", totalPrice);
        return "admin/index";
    }
}