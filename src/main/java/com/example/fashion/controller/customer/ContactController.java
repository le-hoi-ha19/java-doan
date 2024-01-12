package com.example.fashion.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.fashion.models.Brand;
import com.example.fashion.models.Category;
import com.example.fashion.models.Contact;
import com.example.fashion.models.Product;
import com.example.fashion.services.BrandService;
import com.example.fashion.services.CategoryService;
import com.example.fashion.services.ContactService;
import com.example.fashion.services.ProductService;
import com.example.fashion.services.UserService;

@Controller
public class ContactController {

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

    @GetMapping("/contact")
    public String index(Model model) {
        List<Product> listViewsProducts = this.productService.getAll();
        model.addAttribute("listViewsProducts", listViewsProducts);
        List<Category> categories = this.categoryService.getAll();
        model.addAttribute("categories", categories);
        List<Brand> listBra = this.brandService.getAll();
        model.addAttribute("listBra", listBra);
        return "contact/index";
    }

    @PostMapping("/contact")
    public String save(@ModelAttribute("contact") Contact contact, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Nếu có lỗi hợp lệ, trả về trang thêm danh mục với thông báo lỗi
            return "contact/index";
        }

        if (contact.getFullname() == null || contact.getFullname().trim().isEmpty() || contact.getEmail() == null
                || contact.getTelephone() == null || contact.getSubject() == null || contact.getMessage() == null) {
            model.addAttribute("error", "Thông tin không được để trống");
            return "contact/index";
        }

        if (this.contactService.create(contact)) {
            // Thêm thông báo thành công vào model
            model.addAttribute("successMessage", "Tin nhắn của quý khách đã được gửi thành công!");

            // Chuyển hướng đến trang index hoặc trang khác tùy theo yêu cầu của bạn
            return "redirect:/contact/index";
        } else {
            // Nếu có lỗi khi lưu tin nhắn, bạn có thể thêm thông báo lỗi vào model
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi gửi tin nhắn. Vui lòng thử lại.");

            return "redirect:/contact/index";
        }
    }
}
