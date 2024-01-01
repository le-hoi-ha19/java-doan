package com.example.fashion.controller.customer;

import java.security.Principal;
import java.util.UUID;

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
    public String index(HttpServletRequest request, Model model) {
        return "cart/index";
    }

    @PostMapping("/add-to-cart")
	public String addToCart(HttpServletRequest request, Model model, @RequestParam("ProductID") Long ProductID,
			@RequestParam("quantity") int Quantity) {

		// sessiontToken
		String sessionToken = (String) request.getSession(true).getAttribute("sessiontToken");
		if (sessionToken == null) {

			sessionToken = UUID.randomUUID().toString();
			request.getSession().setAttribute("sessiontToken", sessionToken);
			cartService.addItemToCart(ProductID, sessionToken, Quantity);
		} else {
			cartService.addToExistingCart(ProductID, sessionToken, Quantity);
		}
		return "redirect:/";
	}

}
