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
import com.example.fashion.models.Brand;
import com.example.fashion.models.Category;
import com.example.fashion.services.OrderService;
import com.example.fashion.services.UserService;
import com.example.fashion.services.BrandService;
import com.example.fashion.services.CategoryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @GetMapping("/my-profile")
    public String profile(Model model, Principal principal, HttpSession session) {
        if (principal == null) {
            return "redirect:/login";
        }

        String username = principal.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);

        List<Category> categories = this.categoryService.getAll();
        model.addAttribute("categories", categories);
        List<Brand> listBra = this.brandService.getAll();
        model.addAttribute("listBra", listBra);

        return "customer/profile";
    }

    @GetMapping("/my-orders")
    public String myOrders(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        String username = principal.getName();
        User user = userService.findByUsername(username);
        
        List<Order> userOrders = orderService.getAll().stream()
            .filter(order -> order.getUser().getId().equals(user.getId()))
            .toList();
        
        model.addAttribute("orders", userOrders);
        model.addAttribute("user", user);

        List<Category> categories = this.categoryService.getAll();
        model.addAttribute("categories", categories);
        List<Brand> listBra = this.brandService.getAll();
        model.addAttribute("listBra", listBra);

        return "customer/my-orders";
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
