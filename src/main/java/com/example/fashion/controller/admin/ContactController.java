package com.example.fashion.controller.admin;

import java.util.List;

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

import com.example.fashion.models.Contact;
import com.example.fashion.services.ContactService;

@Controller
@RequestMapping("/admin")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contact")
    public String index(Model model) {
        List<Contact> list = this.contactService.getAll();
        model.addAttribute("list", list);
        return "admin/contact/index";
    }

    @GetMapping("/delete-contact/{contactID}")
    public String delete(@PathVariable("contactID") Long contactID) {
        if (this.contactService.delete(contactID)) {
            return "redirect:/admin/contact";
        } else {
            return "redirect:/admin/contact";
        }

    }
}
