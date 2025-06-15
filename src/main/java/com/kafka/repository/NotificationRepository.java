package com.kafka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafka.entity.Notification;

public interface NotificationRepository  extends JpaRepository<Notification, Long>{
  List<Notification> findByUserIdOrderByTimestampDesc(Long userId);
  List<Notification> findByUserIdAndSeenFalseOrderByTimestampDesc(Long userId);
  void deleteByUserId(Long userId);
 
}
