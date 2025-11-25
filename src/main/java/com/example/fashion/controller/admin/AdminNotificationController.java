package com.example.fashion.controller.admin;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.fashion.models.Brand;
import com.example.fashion.models.Category;
import com.example.fashion.models.Notification;
import com.example.fashion.models.User;
import com.example.fashion.services.BrandService;
import com.example.fashion.services.CategoryService;
import com.example.fashion.services.NotificationService;
import com.example.fashion.services.UserService;

@Controller
@RequestMapping("/admin/notifications")
public class AdminNotificationController extends BaseAdminController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @GetMapping
    public String index(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        User admin = userService.findByUsername(principal.getName());
        List<Notification> notifications = notificationService.getAllNotifications(admin.getId());
        long unreadCount = notificationService.countUnreadNotifications(admin.getId());

        model.addAttribute("notifications", notifications);
        model.addAttribute("unreadCount", unreadCount);

        List<Category> categories = this.categoryService.getAll();
        model.addAttribute("categories", categories);
        List<Brand> listBra = this.brandService.getAll();
        model.addAttribute("listBra", listBra);

        return "admin/notifications/index";
    }

    @GetMapping("/count")
    @ResponseBody
    public ResponseEntity<Map<String, Long>> getUnreadCount(Principal principal) {
        if (principal == null) {
            return ResponseEntity.ok(Map.of("count", 0L));
        }

        User admin = userService.findByUsername(principal.getName());
        long count = notificationService.countUnreadNotifications(admin.getId());

        Map<String, Long> response = new HashMap<>();
        response.put("count", count);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/unread")
    @ResponseBody
    public ResponseEntity<List<Notification>> getUnreadNotifications(Principal principal) {
        if (principal == null) {
            return ResponseEntity.ok(List.of());
        }

        User admin = userService.findByUsername(principal.getName());
        List<Notification> notifications = notificationService.getUnreadNotifications(admin.getId());

        return ResponseEntity.ok(notifications);
    }

    @PostMapping("/{id}/mark-read")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> markAsRead(@PathVariable("id") Long id) {
        boolean success = notificationService.markAsRead(id);
        return ResponseEntity.ok(Map.of("success", success));
    }

    @PostMapping("/mark-all-read")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> markAllAsRead(Principal principal) {
        if (principal == null) {
            return ResponseEntity.ok(Map.of("success", false));
        }

        User admin = userService.findByUsername(principal.getName());
        boolean success = notificationService.markAllAsRead(admin.getId());

        return ResponseEntity.ok(Map.of("success", success));
    }
}
