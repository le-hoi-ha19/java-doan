package com.example.fashion.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fashion.services.OrderService;
import com.example.fashion.services.ProductService;
import com.example.fashion.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseAdminController {

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
        model.addAttribute("totalProducts", this.productService.countTotalProducts());
        model.addAttribute("outOfStockProducts", this.productService.countOutOfStockProducts());
        model.addAttribute("lowStockProducts", this.productService.countLowStockProducts());
        model.addAttribute("totalUser", this.userService.countTotalUsers());
        model.addAttribute("totalPrice", this.orderService.countTotalPrice());
        model.addAttribute("totalOrders", this.orderService.countTotalOrders());
        model.addAttribute("pendingOrders", this.orderService.countPendingOrders());
        model.addAttribute("shippingOrders", this.orderService.countShippingOrders());
        model.addAttribute("completedOrders", this.orderService.countCompletedOrders());
        
        return "admin/index";
    }
}