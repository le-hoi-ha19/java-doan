package com.example.fashion.controller.customer;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fashion.models.Brand;
import com.example.fashion.models.Cart;
import com.example.fashion.models.Category;
import com.example.fashion.models.Product;
import com.example.fashion.models.User;
import com.example.fashion.services.BrandService;
import com.example.fashion.services.CategoryService;
import com.example.fashion.services.ProductService;
import com.example.fashion.services.StorageService;
import com.example.fashion.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductsController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    // @RequestMapping(value = { "/index", "/shop-detail" }, method = RequestMethod.POST)
    // public String home(Model model, Principal principal, HttpSession session) {
    //     if (principal != null) {
    //         session.setAttribute("username", principal.getName());
    //         User user = userService.findByUsername(principal.getName());
    //         Cart cart = (Cart) user.getCarts();
    //         session.setAttribute("totalItems", cart.getTotalsItem());
    //     } else {
    //         session.removeAttribute("username");
    //     }
    //     return "shop/detail";
    // }

    @GetMapping("/products")
    public String shop(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Long pageNo) {
        Page<Product> listProducts = this.productService.getAll(pageNo);

        if (listProducts != null) {
            model.addAttribute("totalPage", listProducts.getTotalPages());
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("listProducts", listProducts);
        }

        List<Category> categories = this.categoryService.getAll();
        if (categories != null) {
            model.addAttribute("categories", categories);
        }

        List<Brand> listBra = this.brandService.getAll();
        if (listBra != null) {
            model.addAttribute("listBra", listBra);
        }

        long totalProducts = this.productService.countTotalProducts();
        model.addAttribute("totalProducts", totalProducts);

        return "product/index";
    }

    @GetMapping("/shop-detail/{ProductID}")
    public String detail(Model model, @PathVariable("ProductID") Long ProductID) {
        Product product = this.productService.findByID(ProductID);
        model.addAttribute("Product", product);
        List<Category> categories = this.categoryService.getAll();
        if (categories != null) {
            model.addAttribute("categories", categories);
        }

        List<Brand> listBra = this.brandService.getAll();
        if (listBra != null) {
            model.addAttribute("listBra", listBra);
        }

        List<Product> relatedProducts = productService.findByCategory(product.getCategory());
        relatedProducts.removeIf(p -> p.getProductID().equals(ProductID));
        model.addAttribute("relatedProducts", relatedProducts);

        long totalProducts = productService.countTotalProducts();
        totalProducts--;
        List<Product> allProducts = productService.getAll();
        allProducts.removeIf(p -> p.getProductID().equals(ProductID));
        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("listProducts", allProducts);
        return "product/detail";
    }

    @GetMapping("/products/{CatID}")
    public String category(Model model, @PathVariable("CatID") Integer CatID){
        Category category = this.categoryService.findByID(CatID);
        model.addAttribute("category", category);
        List<Product> lpro= this.productService.findByCategory(category);
        model.addAttribute("lpro", lpro);
        return "product/category";
    }
}
