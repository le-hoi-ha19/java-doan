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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.fashion.models.Brand;
import com.example.fashion.models.Category;
import com.example.fashion.models.Product;
import com.example.fashion.services.BrandService;
import com.example.fashion.services.StorageService;
import com.example.fashion.utils.SlugUtils;

@Controller
@RequestMapping("/admin")
public class BrandController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private BrandService brandService;

    @GetMapping("/brand")
    public String index(Model model) {
        List<Brand> list = this.brandService.getAll();
        model.addAttribute("list", list);
        return "admin/brand/index";
    }

    @GetMapping("/add-brand")
    public String add(Model model) {

        Brand brand = new Brand();
        model.addAttribute("brand", brand);
        return "admin/brand/add";
    }

    @PostMapping("/add-brand")
    public String save(@ModelAttribute("brand") Brand brand, BindingResult bindingResult,
            @RequestParam("fileImag") MultipartFile file, Model model) {

        if (bindingResult.hasErrors()) {
            
            return "admin/brand/add";
        }

        if (brand.getBrandName() == null || brand.getBrandName().trim().isEmpty() || file.isEmpty()) {
            model.addAttribute("error", "Vui lòng điền đầy đủ thông tin bắt buộc");
            return "admin/brand/add";
        }

        try {
            // create slug
            String slug = SlugUtils.createSlug(brand.getBrandName());
            brand.setSlug(slug);
            // upload file
            this.storageService.store(file);
            String fileName = file.getOriginalFilename();
            brand.setLogo(fileName);
            if (this.brandService.create(brand)) {
                return "redirect:/admin/brand";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "admin/brand/add";
    }

    @GetMapping("/edit-brand/{BrandID}")
    public String edit(Model model, @PathVariable("BrandID") Long BrandID) {
        Brand brand = this.brandService.findByID(BrandID);
        model.addAttribute("Brand", brand);
        List<Brand> listBra = this.brandService.getAll();
        model.addAttribute("listBra", listBra);
        return "admin/brand/edit";
    }

    @PostMapping("/edit-brand")
    public String update(@ModelAttribute("brand") Brand brand, BindingResult bindingResult,
            @RequestParam("fileImage") MultipartFile file, Model model) {

        if (bindingResult.hasErrors()) {
            
            List<Brand> listBra = this.brandService.getAll();
            model.addAttribute("listBra", listBra);
            return "admin/brand/edit";
        }

        if (brand.getBrandName() == null || brand.getBrandName().trim().isEmpty() || file.isEmpty()) {
            
            model.addAttribute("error", "Vui lòng điền đầy đủ thông tin bắt buộc");
            List<Brand> listBra = this.brandService.getAll();
            model.addAttribute("listBra", listBra);
            return "admin/brand/edit";
        }

        try {
             // update slug
             String slug = SlugUtils.createSlug(brand.getBrandName());
             brand.setSlug(slug);
            // upload file
            this.storageService.store(file);
            String fileName = file.getOriginalFilename();
            brand.setLogo(fileName);
            if (this.brandService.update(brand)) {
                return "redirect:/admin/brand";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/admin/brand/edit";
    }

    @GetMapping("/delete-brand/{BrandID}")
    public String delete(@PathVariable("BrandID") Long BrandID) {
        if (this.brandService.delete(BrandID)) {
            return "redirect:/admin/brand";
        } else {
            return "redirect:/admin/brand";
        }

    }
}
