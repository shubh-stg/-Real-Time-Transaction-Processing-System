package com.kafka.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafka.dto.NotificationDto;
import com.kafka.entity.Notification;
import com.kafka.mapper.notiMapper;
import com.kafka.repository.NotificationRepository;

import jakarta.transaction.Transactional;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;
	

	public List<NotificationDto> getUserSpecificNotification(Long userId){
		
		List<Notification> byUserIdOrderByTimestampDesc = notificationRepository.findByUserIdOrderByTimestampDesc(userId);
		return byUserIdOrderByTimestampDesc.stream().map(dto-> notiMapper.convertToDto(dto) ).collect(Collectors.toList());
	}
	
	public List<NotificationDto> getUnseenNotifications(Long userId) {
	    List<Notification> unseen = notificationRepository.findByUserIdAndSeenFalseOrderByTimestampDesc(userId);
	    return unseen.stream().map(t->notiMapper.convertToDto(t)).collect(Collectors.toList());
	            
	}
	@Transactional
	public void markAllAsSeen(Long userId) {
	    List<Notification> unseen = notificationRepository.findByUserIdAndSeenFalseOrderByTimestampDesc(userId);
	    unseen.forEach(n -> n.setSeen(true));
	    notificationRepository.saveAll(unseen);
	}
	@Transactional
	public void deleteNotifications(Long userId) {
	    notificationRepository.deleteByUserId(userId);
	}
	
}
