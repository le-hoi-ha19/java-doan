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
        try {
            long totalProducts = this.productService.countTotalProducts();
            model.addAttribute("totalProducts", totalProducts);

            long totalUser = this.userService.countTotalUsers();
            model.addAttribute("totalUser", totalUser);

            long totalPrice = this.orderService.countTotalPrice();
            model.addAttribute("totalPrice", totalPrice);

            long totalOrders = this.orderService.countTotalOrders();
            model.addAttribute("totalOrders", totalOrders);

            long pendingOrders = this.orderService.countPendingOrders();
            model.addAttribute("pendingOrders", pendingOrders);

            long shippingOrders = this.orderService.countShippingOrders();
            model.addAttribute("shippingOrders", shippingOrders);

            long completedOrders = this.orderService.countCompletedOrders();
            model.addAttribute("completedOrders", completedOrders);

            long outOfStockProducts = this.productService.countOutOfStockProducts();
            model.addAttribute("outOfStockProducts", outOfStockProducts);

            long lowStockProducts = this.productService.countLowStockProducts();
            model.addAttribute("lowStockProducts", lowStockProducts);

            return "admin/index";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Lỗi khi tải dữ liệu: " + e.getMessage());
            model.addAttribute("totalProducts", 0L);
            model.addAttribute("totalUser", 0L);
            model.addAttribute("totalPrice", 0L);
            model.addAttribute("totalOrders", 0L);
            model.addAttribute("pendingOrders", 0L);
            model.addAttribute("shippingOrders", 0L);
            model.addAttribute("completedOrders", 0L);
            model.addAttribute("outOfStockProducts", 0L);
            model.addAttribute("lowStockProducts", 0L);
            return "admin/index";
        }
    }
}