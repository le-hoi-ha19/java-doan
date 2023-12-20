package com.example.fashion.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.fashion.models.Cart;
import com.example.fashion.services.CartService;
import com.example.fashion.services.ProductService;
import com.example.fashion.services.UserService;

@Controller
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String index(@ModelAttribute("cart") Cart cart, Model model) {
        if (cart == null || ((CharSequence) cart).isEmpty()) {
            model.addAttribute("check", "Không có sản phẩm trong giỏ hàng");
        }
        model.addAttribute("cart", cart);
        return "cart/index";
    }
}
