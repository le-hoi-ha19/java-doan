package com.example.fashion.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    
    @GetMapping("/login-customer")
        public String Login(){
            return "login";
        }
}
