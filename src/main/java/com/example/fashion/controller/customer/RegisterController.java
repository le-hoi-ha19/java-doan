package com.example.fashion.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.fashion.models.User;
import com.example.fashion.services.StorageService;
import com.example.fashion.services.UserService;
import org.springframework.ui.Model;

@Controller
public class RegisterController {
    @Autowired
    private StorageService storageService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user,
            @RequestParam("imaget") MultipartFile file,
            Model model) {
        try {

            if (!file.isEmpty()) {
                this.storageService.store(file);
                String fileName = file.getOriginalFilename();
                user.setImages(fileName);
            }

            user.setEnabled(true);

            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setFullname(user.getFullname()); 
            user.setGender(user.getGender()); 
            user.setAddress(user.getAddress());
            user.setTelephone(user.getTelephone());
            user.setEmail(user.getEmail()); 
            user.setUsername(user.getUsername()); 

            this.userService.createWithUserRoleCUSTOMER(user);

            return "redirect:/login";

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi tải ảnh lên. Vui lòng thử lại.");
            return "register";
        }
    }

}
