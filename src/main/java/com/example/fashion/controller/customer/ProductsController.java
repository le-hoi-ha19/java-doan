package com.example.fashion.controller.customer;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fashion.models.Brand;
import com.example.fashion.models.Cart;
import com.example.fashion.models.Category;
import com.example.fashion.models.Comment;
import com.example.fashion.models.Product;
import com.example.fashion.models.User;
import com.example.fashion.services.BrandService;
import com.example.fashion.services.CategoryService;
import com.example.fashion.services.CommentService;
import com.example.fashion.services.ProductService;
import com.example.fashion.services.StorageService;
import com.example.fashion.services.UserService;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class ProductsController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    // @RequestMapping(value = { "/index", "/shop-detail" }, method =
    // RequestMethod.POST)
    // public String home(Model model, Principal principal, HttpSession session) {
    // if (principal != null) {
    // session.setAttribute("username", principal.getName());
    // User user = userService.findByUsername(principal.getName());
    // Cart cart = (Cart) user.getCarts();
    // session.setAttribute("totalItems", cart.getTotalsItem());
    // } else {
    // session.removeAttribute("username");
    // }
    // return "shop/detail";
    // }

    @GetMapping("/products")
    public String shop(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Long pageNo) {
        Page<Product> listProducts = this.productService.getAll(pageNo);

        if (listProducts != null) {
            model.addAttribute("totalPage", listProducts.getTotalPages());
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("listProducts", listProducts);
        }

        long totalProducts = this.productService.countTotalProducts();
        model.addAttribute("totalProducts", totalProducts);

        return "product/index";
    }

    @GetMapping("/product-details/{ProductID}")
    @Transactional(readOnly = true)
    public String detail(Model model, @PathVariable("ProductID") Long ProductID) {
        try {
       
        
            Product product = this.productService.findByID(ProductID);
            if (product == null) {
                System.out.println("ERROR: Product ID " + ProductID + " NOT FOUND!");
                System.out.println("=========== DEBUG END ===========");
                model.addAttribute("error", "Sản phẩm không tồn tại!");
                model.addAttribute("message", "Sản phẩm với ID " + ProductID + " không được tìm thấy trong hệ thống.");
                return "error/404";
            }
            

            
            model.addAttribute("Product", product);

            List<Product> relatedProducts = productService.findByCategory(product.getCategory());
            if (relatedProducts != null) {
                relatedProducts.removeIf(p -> p.getProductID().equals(ProductID));
            }
            model.addAttribute("relatedProducts", relatedProducts != null ? relatedProducts : new java.util.ArrayList<>());

            long totalProducts = productService.countTotalProducts();
            if (totalProducts > 0) totalProducts--;
            List<Product> allProducts = productService.getAll();
            if (allProducts != null) {
                allProducts.removeIf(p -> p.getProductID().equals(ProductID));
            }
            model.addAttribute("totalProducts", totalProducts);
            model.addAttribute("listProducts", allProducts != null ? allProducts : new java.util.ArrayList<>());
            
            List<Comment> comments = this.commentService.getCommentByProductId(ProductID);
            model.addAttribute("comments", comments != null ? comments : new java.util.ArrayList<>());
            
            return "product/detail";
        } catch (Exception e) {
            System.err.println("=========== EXCEPTION ===========");
            System.err.println("Error in product-details: " + e.getMessage());
            e.printStackTrace();
            System.err.println("=========== EXCEPTION END ===========");
            model.addAttribute("error", "Lỗi khi tải chi tiết sản phẩm: " + e.getMessage());
            return "error/404";
        }
    }

    @GetMapping("/products-category/{slug}")
    public String category(Model model, @PathVariable("slug") String slug) {
        Category category = this.categoryService.findBySlug(slug);
        model.addAttribute("category", category);
        List<Product> lpro = this.productService.findByCategory(category);
        model.addAttribute("lpro", lpro);
        return "product/category";
    }

    @GetMapping("/products-branch/{slug}")
    public String brand(Model model, @PathVariable("slug") String slug) {
        Brand brand = this.brandService.findBySlug(slug);
        model.addAttribute("brand", brand);

        List<Product> lpro = this.productService.findByBrand(brand);
        model.addAttribute("lpro", lpro);

        return "product/brand";
    }

}
