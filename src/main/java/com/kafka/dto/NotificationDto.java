package com.kafka.dto;

import java.time.LocalDateTime;

import com.kafka.entity.NotificationType;

public class NotificationDto {
	
	private Long id;
    private Long userId;
    private String message;
    private NotificationType type;
    private LocalDateTime timestamp;
	public NotificationDto(Long id, Long userId, String message, NotificationType type, LocalDateTime timestamp) {
		super();
		this.id = id;
		this.userId = userId;
		this.message = message;
		this.type = type;
		this.timestamp = timestamp;
	}
	
	public NotificationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public NotificationType getType() {
		return type;
	}
	public void setType(NotificationType type) {
		this.type = type;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "NotificationDto [id=" + id + ", userId=" + userId + ", message=" + message + ", type=" + type
				+ ", timestamp=" + timestamp + "]";
	}

    
}
