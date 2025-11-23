package com.example.fashion.controller.customer;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.fashion.models.Order;
import com.example.fashion.models.User;
import com.example.fashion.services.OrderService;
import com.example.fashion.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/my-profile")
    public String profile(Model model, Principal principal, HttpSession session) {
        if (principal == null) {
            return "redirect:/login";
        }

        String username = principal.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);

        return "customer/profile";
    }

    @GetMapping("/my-orders")
    public String myOrders(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        try {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            
            if (user == null) {
                return "redirect:/login";
            }
            
            List<Order> userOrders = orderService.findByUserOrderByOrderIDDesc(user);
            
            model.addAttribute("orders", userOrders);
            model.addAttribute("user", user);

            return "customer/my-orders";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi tải đơn hàng.");
            return "customer/my-orders";
        }
    }

    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute("user") User user, Principal principal, HttpSession session) {
        if (principal == null) {
            return "redirect:/login";
        }

        try {
            User existingUser = userService.findByUsername(principal.getName());
            existingUser.setFullname(user.getFullname());
            existingUser.setEmail(user.getEmail());
            existingUser.setTelephone(user.getTelephone());
            existingUser.setAddress(user.getAddress());
            existingUser.setGender(user.getGender());
            
            userService.update(existingUser);
            session.setAttribute("successMessage", "✅ Cập nhật thông tin thành công!");
        } catch (Exception e) {
            session.setAttribute("errorMessage", "❌ Cập nhật thất bại!");
            e.printStackTrace();
        }

        return "redirect:/my-profile";
    }
}
