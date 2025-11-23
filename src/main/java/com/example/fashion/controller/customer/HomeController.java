package com.example.fashion.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.fashion.models.Brand;
import com.example.fashion.models.Cart;
import com.example.fashion.models.Category;
import com.example.fashion.models.Product;
import com.example.fashion.models.User;
import com.example.fashion.services.BrandService;
import com.example.fashion.services.CategoryService;
import com.example.fashion.services.ProductService;
import com.example.fashion.services.UserService;

import jakarta.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @RequestMapping(value = { "/index", "/" }, method = RequestMethod.POST)
    public String home(Model model, Principal principal, HttpSession session) {
        if (principal != null) {
            session.setAttribute("username", principal.getName());
        } else {
            session.setAttribute("username", "");
        }
        return "index";
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Product> listViewsProducts = this.productService.getAll();
        model.addAttribute("listViewsProducts", listViewsProducts);
        List<Product> lstNewProducts = this.productService.list6Products();
        model.addAttribute("lstNewProducts", lstNewProducts);
        List<Category> categories = this.categoryService.getAll();
        model.addAttribute("categories", categories);
        List<Brand> listBra = this.brandService.getAll();
        model.addAttribute("listBra", listBra);
        return "home/index";
    }
}
