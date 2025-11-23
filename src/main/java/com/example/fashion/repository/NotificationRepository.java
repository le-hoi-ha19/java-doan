package com.example.fashion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fashion.models.Notification;

@Repository
public interface NotificationRepository  extends JpaRepository<Notification, Long> {
    List<Notification> findByUserIdAndIsReadFalse(Long userId);
    long countByUserIdAndIsReadFalse(Long userId);
    List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);
}
