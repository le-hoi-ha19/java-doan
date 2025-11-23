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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            @RequestParam("fileImages") MultipartFile[] fileImages, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            List<Category> listCat = this.categoryService.getAll();
            model.addAttribute("listCat", listCat);
            List<Brand> listBra = this.brandService.getAll();
            model.addAttribute("listBra", listBra);
            return "admin/product/add";
        }

        if (product.getProductName() == null || product.getProductName().trim().isEmpty() ||
                fileAvatar.isEmpty() || product.getPrice() == null || product.getSalePrice() == null
                || product.getQuantity() == null) {
            model.addAttribute("error", "Vui lòng điền đầy đủ thông tin bắt buộc");
            List<Category> listCat = this.categoryService.getAll();
            model.addAttribute("listCat", listCat);
            List<Brand> listBra = this.brandService.getAll();
            model.addAttribute("listBra", listBra);
            return "admin/product/add";
        }

        try {
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
                redirectAttributes.addFlashAttribute("successMessage", "✅ Thêm sản phẩm thành công!");
                return "redirect:/admin/product";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "❌ Có lỗi xảy ra khi thêm sản phẩm.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "❌ Có lỗi xảy ra: " + e.getMessage());
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
            @RequestParam("fileImages") MultipartFile[] fileImages, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            List<Category> listCat = this.categoryService.getAll();
            model.addAttribute("listCat", listCat);
            List<Brand> listBra = this.brandService.getAll();
            model.addAttribute("listBra", listBra);
            return "admin/product/edit";
        }

        if (product.getProductName() == null || product.getProductName().trim().isEmpty() ||
                product.getPrice() == null || product.getSalePrice() == null
                || product.getQuantity() == null) {
            model.addAttribute("error", "Vui lòng điền đầy đủ thông tin bắt buộc");
            List<Category> listCat = this.categoryService.getAll();
            model.addAttribute("listCat", listCat);
            List<Brand> listBra = this.brandService.getAll();
            model.addAttribute("listBra", listBra);
            return "admin/product/edit";
        }

        try {
            Product existingProduct = this.productService.findByID(product.getProductID());
            
            if (!fileAvatar.isEmpty()) {
                if (existingProduct.getAvatar() != null) {
                    this.storageService.delete(existingProduct.getAvatar());
                }
                this.storageService.store(fileAvatar);
                product.setAvatar(fileAvatar.getOriginalFilename());
            } else {
                product.setAvatar(existingProduct.getAvatar());
            }
    
            if (fileImages != null && fileImages.length > 0) {
                for (int i = 0; i < Math.min(fileImages.length, 3); i++) {
                    if (!fileImages[i].isEmpty()) {
                        String oldImage = null;
                        switch (i) {
                            case 0:
                                oldImage = existingProduct.getImg1();
                                break;
                            case 1:
                                oldImage = existingProduct.getImg2();
                                break;
                            case 2:
                                oldImage = existingProduct.getImg3();
                                break;
                        }
                        if (oldImage != null) {
                            this.storageService.delete(oldImage);
                        }
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
                    } else {
                        switch (i) {
                            case 0:
                                product.setImg1(existingProduct.getImg1());
                                break;
                            case 1:
                                product.setImg2(existingProduct.getImg2());
                                break;
                            case 2:
                                product.setImg3(existingProduct.getImg3());
                                break;
                        }
                    }
                }
            } else {
                product.setImg1(existingProduct.getImg1());
                product.setImg2(existingProduct.getImg2());
                product.setImg3(existingProduct.getImg3());
            }
    
            if (this.productService.update(product)) {
                redirectAttributes.addFlashAttribute("successMessage", "✅ Cập nhật sản phẩm thành công!");
                return "redirect:/admin/product";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "❌ Có lỗi xảy ra khi cập nhật sản phẩm.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "❌ Có lỗi xảy ra: " + e.getMessage());
        }

        return "admin/product/edit";
    }

    @GetMapping("/delete-product/{ProductID}")
    public String delete(@PathVariable("ProductID") Long ProductID) {
        try {
            Product product = this.productService.findByID(ProductID);
            if (product != null) {
                this.storageService.delete(product.getAvatar());
                this.storageService.delete(product.getImg1());
                this.storageService.delete(product.getImg2());
                this.storageService.delete(product.getImg3());
            }
            this.productService.delete(ProductID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/product";
    }

}
