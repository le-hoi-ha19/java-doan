package com.example.fashion.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/not-found")
    public String handle404() {
        return "error/404";
    }
}
