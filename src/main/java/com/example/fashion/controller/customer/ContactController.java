package com.example.fashion.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.fashion.models.Contact;
import com.example.fashion.services.ContactService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contact")
    public String index(Model model) {
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        return "contact/index";
    }

    @PostMapping("/contact")
    public String save(@ModelAttribute("contact") Contact contact, BindingResult bindingResult, 
                      Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Vui lòng kiểm tra lại thông tin!");
            return "contact/index";
        }

        if (contact.getFullname() == null || contact.getFullname().trim().isEmpty() || contact.getEmail() == null
                || contact.getTelephone() == null || contact.getSubject() == null || contact.getMessage() == null) {
            model.addAttribute("error", "Thông tin không được để trống");
            return "contact/index";
        }

        try {
            if (this.contactService.create(contact)) {
                session.setAttribute("successMessage", "✅ Tin nhắn của quý khách đã được gửi thành công!");
                return "redirect:/contact";
            } else {
                session.setAttribute("errorMessage", "❌ Có lỗi xảy ra khi gửi tin nhắn. Vui lòng thử lại.");
                return "redirect:/contact";
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "❌ Có lỗi xảy ra: " + e.getMessage());
            return "redirect:/contact";
        }
    }
}
