package com.example.fashion.config;

import java.util.List;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.fashion.models.Brand;
import com.example.fashion.models.Category;
import com.example.fashion.models.CartItem;
import com.example.fashion.models.User;
import com.example.fashion.services.BrandService;
import com.example.fashion.services.CartItemService;
import com.example.fashion.services.CategoryService;
import com.example.fashion.services.UserService;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @ModelAttribute("categories")
    public List<Category> populateCategories() {
        try {
            List<Category> categories = categoryService.getAll();
            return categories != null ? categories : new java.util.ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }

    @ModelAttribute("totalItem")
    public Integer populateTotalItem(Principal principal) {
        if (principal == null) {
            return 0;
        }
        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return 0;
        }
        List<CartItem> items = cartItemService.findByUser(user.getId());
        int total = 0;
        for (CartItem item : items) {
            if (item != null && item.getQuantity() != null) {
                total += item.getQuantity();
            }
        }
        return total;
    }

    @ModelAttribute("listBra")
    public List<Brand> populateBrands() {
        try {
            List<Brand> brands = brandService.getAll();
            return brands != null ? brands : new java.util.ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }
}
