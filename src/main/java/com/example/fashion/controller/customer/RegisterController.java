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
            // Xử lý lưu ảnh đại diện
            if (!file.isEmpty()) {
                this.storageService.store(file); // Phương thức để lưu file
                String fileName = file.getOriginalFilename();
                user.setImages(fileName); // Lưu tên file ảnh vào thuộc tính của user
            }

            // Thiết lập các thuộc tính cho user
            user.setEnabled(true);

            // Mã hóa mật khẩu
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            // Cập nhật thông tin người dùng
            user.setFullname(user.getFullname()); // Cập nhật họ và tên
            user.setGender(user.getGender()); // Cập nhật giới tính
            user.setAddress(user.getAddress()); // Cập nhật địa chỉ
            user.setTelephone(user.getTelephone()); // Cập nhật số điện thoại
            user.setEmail(user.getEmail()); // Cập nhật email
            user.setUsername(user.getUsername()); // Cập nhật tên tài khoản

            // Lưu thông tin người dùng vào database
            this.userService.createWithUserRoleCUSTOMER(user);

            return "redirect:/login"; // Điều hướng tới trang đăng nhập sau khi đăng ký thành công

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi tải ảnh lên. Vui lòng thử lại.");
            return "register"; // Trả về trang đăng ký nếu có lỗi
        }
    }

}
