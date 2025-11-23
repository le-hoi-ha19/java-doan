package com.example.fashion.services;

import java.util.List;

import com.example.fashion.models.Notification;



public interface NotificationService {
    void createNotification(Long userId, String title, String message);
    List<Notification> getUnreadNotifications(Long userId);
    boolean markAsRead(Long notificationId);
    long countUnreadNotifications(Long userId);
    List<Notification> getAllNotifications(Long userId);
    boolean markAllAsRead(Long userId);
}
