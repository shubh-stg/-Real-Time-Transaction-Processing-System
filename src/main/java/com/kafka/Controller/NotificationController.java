package com.kafka.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.Service.NotificationService;
import com.kafka.dto.NotificationDto;

@RestController
@RequestMapping("/api/noti")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<NotificationDto>> getAllNotiForUser(@PathVariable Long userId){
		List<NotificationDto> unseenNotifications = notificationService.getUserSpecificNotification(userId);
		return new ResponseEntity<>(unseenNotifications,HttpStatus.OK);
	}
	
    @GetMapping("/{userId}/unseen")
    public ResponseEntity<List<NotificationDto>> getUnseenNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getUnseenNotifications(userId));
    }
    
    @PostMapping("/{userId}/mark-seen")
    public ResponseEntity<String> markAllSeen(@PathVariable Long userId) {
        notificationService.markAllAsSeen(userId);
        return ResponseEntity.ok("Marked all as seen");
    }

    // 4. Delete all notifications for a user
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteAllNotifications(@PathVariable Long userId) {
        notificationService.deleteNotifications(userId);
        return ResponseEntity.ok("Notification deleted");
    }
	
}
