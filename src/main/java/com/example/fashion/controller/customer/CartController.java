package com.example.fashion.controller.customer;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fashion.models.Cart;
import com.example.fashion.models.Product;
import com.example.fashion.models.User;
import com.example.fashion.services.CartService;
import com.example.fashion.services.ProductService;
import com.example.fashion.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String index(@ModelAttribute("cart") Cart cart, Model model, HttpSession session) {
        if (cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
            model.addAttribute("check", "Không có sản phẩm trong giỏ hàng");
        }
        session.setAttribute("totalsItem", cart.getTotalsItem());
        model.addAttribute("subTotal", cart.getTotalsPrice());
        model.addAttribute("cart", cart);
        return "cart/index";
    }

    @PostMapping("/add-to-cart")
    public String addItemToCart(
            @RequestParam("id") Long ProductID,
            @RequestParam(value = "quantity", required = false, defaultValue = "1") Integer Quantity,
            HttpServletRequest request) {

        // if (principal == null) {
        // return "redirect:/login";
        // }

        Cart cart = (Cart) request.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.setAttribute("cart", cart);
        }

        Product product = productService.findByID(ProductID);
        // String username = principal.getName();
        // User user = userService.findByUsername(username);

        this.cartService.addItemToCart(product, Quantity);

        // Chuyển hướng người dùng trở lại trang trước đó
        return "redirect:" + request.getHeader("Referer");
    }

}
