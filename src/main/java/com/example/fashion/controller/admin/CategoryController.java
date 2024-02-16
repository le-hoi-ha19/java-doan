package com.example.fashion.controller.admin;

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

import com.example.fashion.models.Category;
import com.example.fashion.services.CategoryService;

@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String index(Model model) {
        List<Category> list = this.categoryService.getAll();
        model.addAttribute("list", list);
        return "admin/category/index";
    }

    @GetMapping("/add-category")
    public String add(Model model) {

        Category category = new Category();
        model.addAttribute("category", category);
        return "admin/category/add";
    }

    @PostMapping("/add-category")
    public String save(@ModelAttribute("category") Category category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Nếu có lỗi hợp lệ, trả về trang thêm danh mục với thông báo lỗi
            return "admin/category/add";
        }

        if (category.getCatName() == null || category.getCatName().trim().isEmpty()) {
            // Nếu tên thể loại rỗng, thêm thông báo lỗi vào model và trả về trang thêm danh
            // mục
            model.addAttribute("error", "Tên thể loại không được để trống");
            return "admin/category/add";
        }

        // Tiến hành thêm danh mục nếu không có lỗi
        if (this.categoryService.create(category)) {
            return "redirect:/admin/category";
        } else {
            return "redirect:/admin/category/add";
        }
    }

    @GetMapping("/edit-category/{CatID}")
    public String edit(Model model, @PathVariable("CatID") Integer CatID) {
        Category category = this.categoryService.findByID(CatID);
        model.addAttribute("category", category);
        return "admin/category/edit";
    }

    @PostMapping("/edit-category")
    public String update(@ModelAttribute("category") Category category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Nếu có lỗi hợp lệ, trả về trang sửa danh mục với thông báo lỗi
            return "admin/category/edit";
        }

        if (category.getCatName() == null || category.getCatName().trim().isEmpty()) {
            // Nếu tên thể loại rỗng, thêm thông báo lỗi vào model và trả về trang sửa danh
            // mục
            model.addAttribute("error", "Tên thể loại không được để trống");
            return "admin/category/edit";
        }

        // Tiến hành cập nhật danh mục nếu không có lỗi
        if (this.categoryService.update(category)) {
            return "redirect:/admin/category";
        } else {
            return "redirect:/admin/category/edit";
        }
    }

    @GetMapping("/delete-category/{CatID}")
    public String delete(@PathVariable("CatID") Integer CatID) {
        if (this.categoryService.delete(CatID)) {
            return "redirect:/admin/category";
        } else {
            return "redirect:/admin/category";
        }

    }

}
