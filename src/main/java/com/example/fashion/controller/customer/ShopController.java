package com.example.fashion.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fashion.models.Brand;
import com.example.fashion.models.Category;
import com.example.fashion.models.Product;
import com.example.fashion.services.BrandService;
import com.example.fashion.services.CategoryService;
import com.example.fashion.services.ProductService;
import com.example.fashion.services.StorageService;

@Controller
public class ShopController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductService productService;

    @GetMapping("/shop")
    public String shop(Model model, @RequestParam(name = "pageNo",  defaultValue = "1") Long pageNo ) {

        Page<Product> listProducts = this.productService.getAll(pageNo);
        model.addAttribute("listProducts", listProducts);
        List<Category> categories = this.categoryService.getAll();
        model.addAttribute("categories", categories);
        List<Brand> listBra = this.brandService.getAll();
        model.addAttribute("listBra", listBra);
        return "shop/index";
    }

    @GetMapping("/shop-detail/{ProductID}")
    public String detail(Model model, @PathVariable("ProductID") Long ProductID) {
        Product product = this.productService.findByID(ProductID);
        model.addAttribute("Product", product);
        return "shop/detail";
    }
}
