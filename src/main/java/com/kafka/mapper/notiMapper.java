package com.kafka.mapper;

import com.kafka.dto.NotificationDto;
import com.kafka.entity.Notification;

public class notiMapper {
	public static NotificationDto convertToDto(Notification notification) {
	    NotificationDto dto = new NotificationDto();
	    dto.setId(notification.getId());
	    dto.setUserId(notification.getUserId());
	    dto.setMessage(notification.getMessage());
	    dto.setTimestamp(notification.getTimestamp());
	    dto.setType(notification.getType());
	    return dto;
	}
	public static Notification convertToNoti(NotificationDto dto) {
		Notification notification = new Notification();
		notification.setId(dto.getId());
		notification.setUserId(dto.getUserId());
		notification.setMessage(dto.getMessage());
		notification.setTimestamp(dto.getTimestamp());
		notification.setType(dto.getType());
		return notification;
	}
}
