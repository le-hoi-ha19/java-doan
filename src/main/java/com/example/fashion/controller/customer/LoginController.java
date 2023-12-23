package com.example.fashion.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.fashion.models.Role;
import com.example.fashion.models.User;
import com.example.fashion.models.UserRole;
import com.example.fashion.services.RoleService;
import com.example.fashion.services.StorageService;
import com.example.fashion.services.UserRoleService;
import com.example.fashion.services.UserService;

@Controller
public class LoginController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // @PostMapping("/login")
    // public String register(@ModelAttribute("user") User user,
    //         @RequestParam("imagec") MultipartFile file) {

    //     this.storageService.store(file);
    //     String fileName = file.getOriginalFilename();
    //     user.setImages(fileName);
    //     user.setEnabled(true);

    //     String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
    //     user.setPassword(encodedPassword);

    //     if (this.userService.createWithUserRoleCUSTOMER(user)) {
    //         return "redirect:login";
    //     }
    //     return "login";

    // }
}
