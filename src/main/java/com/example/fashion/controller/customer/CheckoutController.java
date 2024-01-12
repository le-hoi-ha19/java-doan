package com.example.fashion.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.fashion.models.Brand;
import com.example.fashion.models.Category;
import com.example.fashion.models.Product;
import com.example.fashion.services.BrandService;
import com.example.fashion.services.CategoryService;
import com.example.fashion.services.ContactService;
import com.example.fashion.services.ProductService;
import com.example.fashion.services.UserService;

@Controller
public class CheckoutController {
     @Autowired
    private ContactService contactService;

    @Autowired
    private ProductService productService;

     @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @GetMapping("/checkout")
    public String index(Model model) {
        List<Product> listViewsProducts = this.productService.getAll();
        model.addAttribute("listViewsProducts", listViewsProducts);
        List<Category> categories = this.categoryService.getAll();
        model.addAttribute("categories", categories);
        List<Brand> listBra = this.brandService.getAll();
        model.addAttribute("listBra", listBra);
        return "checkout/index";
    }
}
