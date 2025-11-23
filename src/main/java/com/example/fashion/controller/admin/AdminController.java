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
        StringBuilder debugInfo = new StringBuilder("🔍 ADMIN DEBUG:\n");
        
        // Test ProductService queries
        try {
            long totalProducts = this.productService.countTotalProducts();
            model.addAttribute("totalProducts", totalProducts);
            debugInfo.append("✅ countTotalProducts: ").append(totalProducts).append("\n");
        } catch (Exception e) {
            debugInfo.append("❌ countTotalProducts ERROR: ").append(e.getMessage()).append("\n");
            model.addAttribute("totalProducts", 0L);
            e.printStackTrace();
        }

        try {
            long outOfStockProducts = this.productService.countOutOfStockProducts();
            model.addAttribute("outOfStockProducts", outOfStockProducts);
            debugInfo.append("✅ countOutOfStockProducts: ").append(outOfStockProducts).append("\n");
        } catch (Exception e) {
            debugInfo.append("❌ countOutOfStockProducts ERROR: ").append(e.getMessage()).append("\n");
            model.addAttribute("outOfStockProducts", 0L);
            e.printStackTrace();
        }

        try {
            long lowStockProducts = this.productService.countLowStockProducts();
            model.addAttribute("lowStockProducts", lowStockProducts);
            debugInfo.append("✅ countLowStockProducts: ").append(lowStockProducts).append("\n");
        } catch (Exception e) {
            debugInfo.append("❌ countLowStockProducts ERROR: ").append(e.getMessage()).append("\n");
            model.addAttribute("lowStockProducts", 0L);
            e.printStackTrace();
        }

        // Test UserService queries
        try {
            long totalUser = this.userService.countTotalUsers();
            model.addAttribute("totalUser", totalUser);
            debugInfo.append("✅ countTotalUsers: ").append(totalUser).append("\n");
        } catch (Exception e) {
            debugInfo.append("❌ countTotalUsers ERROR: ").append(e.getMessage()).append("\n");
            model.addAttribute("totalUser", 0L);
            e.printStackTrace();
        }

        // Test OrderService queries
        try {
            long totalPrice = this.orderService.countTotalPrice();
            model.addAttribute("totalPrice", totalPrice);
            debugInfo.append("✅ countTotalPrice: ").append(totalPrice).append("\n");
        } catch (Exception e) {
            debugInfo.append("❌ countTotalPrice ERROR: ").append(e.getMessage()).append("\n");
            model.addAttribute("totalPrice", 0L);
            e.printStackTrace();
        }

        try {
            long totalOrders = this.orderService.countTotalOrders();
            model.addAttribute("totalOrders", totalOrders);
            debugInfo.append("✅ countTotalOrders: ").append(totalOrders).append("\n");
        } catch (Exception e) {
            debugInfo.append("❌ countTotalOrders ERROR: ").append(e.getMessage()).append("\n");
            model.addAttribute("totalOrders", 0L);
            e.printStackTrace();
        }

        try {
            long pendingOrders = this.orderService.countPendingOrders();
            model.addAttribute("pendingOrders", pendingOrders);
            debugInfo.append("✅ countPendingOrders: ").append(pendingOrders).append("\n");
        } catch (Exception e) {
            debugInfo.append("❌ countPendingOrders ERROR: ").append(e.getMessage()).append("\n");
            model.addAttribute("pendingOrders", 0L);
            e.printStackTrace();
        }

        try {
            long shippingOrders = this.orderService.countShippingOrders();
            model.addAttribute("shippingOrders", shippingOrders);
            debugInfo.append("✅ countShippingOrders: ").append(shippingOrders).append("\n");
        } catch (Exception e) {
            debugInfo.append("❌ countShippingOrders ERROR: ").append(e.getMessage()).append("\n");
            model.addAttribute("shippingOrders", 0L);
            e.printStackTrace();
        }

        try {
            long completedOrders = this.orderService.countCompletedOrders();
            model.addAttribute("completedOrders", completedOrders);
            debugInfo.append("✅ countCompletedOrders: ").append(completedOrders).append("\n");
        } catch (Exception e) {
            debugInfo.append("❌ countCompletedOrders ERROR: ").append(e.getMessage()).append("\n");
            model.addAttribute("completedOrders", 0L);
            e.printStackTrace();
        }
        
        // Add debug info for display
        model.addAttribute("debugInfo", debugInfo.toString().replace("\n", "<br>"));
        
        // Print debug info to console
        System.out.println("=== ADMIN DASHBOARD DEBUG ===");
        System.out.println(debugInfo.toString());
        System.out.println("=============================");
        
        return "admin/index";
    }
}