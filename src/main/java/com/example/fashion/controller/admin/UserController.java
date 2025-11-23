package com.example.fashion.controller.admin;

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
@RequestMapping("/admin")
public class UserController {

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

	@GetMapping("/user")
	public String user(Model model) {
		List<User> customerUsers = userService.getCustomerUsers();
		model.addAttribute("customerUsers", customerUsers);
		return "admin/user/index";
	}

	@GetMapping("/delete-user/{id}")
    public String delete(@PathVariable("id") Long id) {
        if (this.userService.delete(id)) {
            return "redirect:/admin/user";
        } else {
            return "redirect:/admin/user";
        }

    }
}
