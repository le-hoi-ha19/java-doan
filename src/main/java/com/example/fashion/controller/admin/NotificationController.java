package com.example.fashion.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fashion.models.Notification;
import com.example.fashion.services.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public String createNotification(@RequestParam Long userId, @RequestParam String title,
            @RequestParam String message) {
        notificationService.createNotification(userId, title, message);
        return "Notification created!";
    }

    @GetMapping("/unread")
    public List<Notification> getUnreadNotifications(@RequestParam Long userId) {
        return notificationService.getUnreadNotifications(userId);
    }

    @PutMapping("/{notificationId}/read")
    public String markAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
        return "Notification marked as read!";
    }
}
