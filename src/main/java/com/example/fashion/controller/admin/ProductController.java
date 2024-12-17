package com.example.fashion.controller.admin;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.fashion.models.Brand;
import com.example.fashion.models.Category;
import com.example.fashion.models.Product;
import com.example.fashion.services.BrandService;
import com.example.fashion.services.CategoryService;
import com.example.fashion.services.ProductService;
import com.example.fashion.services.StorageService;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Controller
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public String index(Model model) {
        List<Product> listProducts = this.productService.getAll();
        model.addAttribute("listProducts", listProducts);
        return "admin/product/index";
    }

    @GetMapping("/add-product")
    public String add(Model model) {

        Product product = new Product();
        model.addAttribute("product", product);
        List<Category> listCat = this.categoryService.getAll();
        model.addAttribute("listCat", listCat);
        List<Brand> listBra = this.brandService.getAll();
        model.addAttribute("listBra", listBra);
        return "admin/product/add";
    }

    @PostMapping("/add-product")
    public String save(@ModelAttribute("product") Product product, BindingResult bindingResult,@RequestParam("fileAvatar") MultipartFile fileAvatar,
            @RequestParam("fileImages") MultipartFile[] fileImages, Model model) {

        if (bindingResult.hasErrors()) {
            // Nếu có lỗi hợp lệ, trả về trang thêm sản phẩm với thông báo lỗi
            List<Category> listCat = this.categoryService.getAll();
            model.addAttribute("listCat", listCat);
            List<Brand> listBra = this.brandService.getAll();
            model.addAttribute("listBra", listBra);
            return "admin/product/add";
        }

        if (product.getProductName() == null || product.getProductName().trim().isEmpty() ||
                fileAvatar.isEmpty() || product.getPrice() == null || product.getSalePrice() == null
                || product.getQuantity() == null) {
            // Nếu các trường quan trọng để trống, thêm thông báo lỗi vào model và trả về
            // trang thêm sản phẩm
            model.addAttribute("error", "Vui lòng điền đầy đủ thông tin bắt buộc");
            List<Category> listCat = this.categoryService.getAll();
            model.addAttribute("listCat", listCat);
            List<Brand> listBra = this.brandService.getAll();
            model.addAttribute("listBra", listBra);
            return "admin/product/add";
        }

        // Tiến hành thêm sản phẩm nếu không có lỗi
        try {
            // upload file và lưu vào trường avatar
            this.storageService.store(fileAvatar);
            String fileNameAvatar = fileAvatar.getOriginalFilename();
            product.setAvatar(fileNameAvatar);
    
            for (int i = 0; i < Math.min(fileImages.length, 3); i++) {
                this.storageService.store(fileImages[i]);
                String fileName = fileImages[i].getOriginalFilename();
    
                switch (i) {
                    case 0:
                        product.setImg1(fileName);
                        break;
                    case 1:
                        product.setImg2(fileName);
                        break;
                    case 2:
                        product.setImg3(fileName);
                        break;
                }
            }
    
            if (this.productService.create(product)) {
                return "redirect:/admin/product";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "admin/product/add";
    }

    @GetMapping("/edit-product/{ProductID}")
    public String edit(Model model, @PathVariable("ProductID") Long ProductID) {
        Product product = this.productService.findByID(ProductID);
        model.addAttribute("Product", product);
        List<Category> listCat = this.categoryService.getAll();
        model.addAttribute("listCat", listCat);
        List<Brand> listBra = this.brandService.getAll();
        model.addAttribute("listBra", listBra);
        return "admin/product/edit";
    }

    @PostMapping("/edit-product")
    public String edit(@ModelAttribute("product") Product product, BindingResult bindingResult,
            @RequestParam("fileAvatar") MultipartFile fileAvatar,
            @RequestParam("fileImages") MultipartFile[] fileImages, Model model) {

        if (bindingResult.hasErrors()) {
            // Nếu có lỗi hợp lệ, trả về trang sửa sản phẩm với thông báo lỗi
            List<Category> listCat = this.categoryService.getAll();
            model.addAttribute("listCat", listCat);
            List<Brand> listBra = this.brandService.getAll();
            model.addAttribute("listBra", listBra);
            return "admin/product/edit";
        }

        if (product.getProductName() == null || product.getProductName().trim().isEmpty() ||
                fileAvatar.isEmpty() || product.getPrice() == null || product.getSalePrice() == null
                || product.getQuantity() == null) {
            // Nếu các trường quan trọng để trống, thêm thông báo lỗi vào model và trả về
            // trang sửa sản phẩm
            model.addAttribute("error", "Vui lòng điền đầy đủ thông tin bắt buộc");
            List<Category> listCat = this.categoryService.getAll();
            model.addAttribute("listCat", listCat);
            List<Brand> listBra = this.brandService.getAll();
            model.addAttribute("listBra", listBra);
            return "admin/product/edit";
        }

        // Tiến hành thêm sản phẩm nếu không có lỗi
        try {
            // upload file và lưu vào trường avatar
            this.storageService.store(fileAvatar);
            String fileNameAvatar = fileAvatar.getOriginalFilename();
            product.setAvatar(fileNameAvatar);
    
            for (int i = 0; i < Math.min(fileImages.length, 3); i++) {
                this.storageService.store(fileImages[i]);
                String fileName = fileImages[i].getOriginalFilename();
    
                switch (i) {
                    case 0:
                        product.setImg1(fileName);
                        break;
                    case 1:
                        product.setImg2(fileName);
                        break;
                    case 2:
                        product.setImg3(fileName);
                        break;
                }
            }
    
            if (this.productService.create(product)) {
                return "redirect:/admin/product";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/admin/product/edit";
    }

    @GetMapping("/delete-product/{ProductID}")
    public String delete(@PathVariable("ProductID") Long ProductID) {
        if (this.productService.delete(ProductID)) {
            return "redirect:/admin/product";
        } else {
            return "redirect:/admin/product";
        }

    }

}
