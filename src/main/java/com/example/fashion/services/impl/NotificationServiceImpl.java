package com.example.fashion.services.impl;
import com.example.fashion.models.Notification;
import com.example.fashion.repository.NotificationRepository;
import com.example.fashion.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void createNotification(Long userId, String title, String message) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setTitle(title);
        notification.setMessage(message);
        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByUserIdAndIsReadFalse(userId);
    }

    @Override
    public boolean markAsRead(Long notificationId) {
        try {
            Notification notification = notificationRepository.findById(notificationId)
                    .orElseThrow(() -> new RuntimeException("Notification not found"));
            notification.setIsRead(true);
            notificationRepository.save(notification);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public long countUnreadNotifications(Long userId) {
        return notificationRepository.countByUserIdAndIsReadFalse(userId);
    }

    @Override
    public List<Notification> getAllNotifications(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public boolean markAllAsRead(Long userId) {
        try {
            List<Notification> unreadNotifications = notificationRepository.findByUserIdAndIsReadFalse(userId);
            for (Notification notification : unreadNotifications) {
                notification.setIsRead(true);
                notificationRepository.save(notification);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
