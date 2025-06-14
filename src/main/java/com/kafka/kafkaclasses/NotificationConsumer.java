package com.kafka.kafkaclasses;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.entity.Notification;
import com.kafka.repository.NotificationRepository;

@Service
public class NotificationConsumer {

    
	@Autowired
    private  ObjectMapper objectMapper ;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	
	@KafkaListener(
		    topics = "notification-topic",
		    groupId = "notification-group",
		    containerFactory = "notificationKafkaListenerFactory"
		)
	public void consumer(String jsonMessage) {
		
		try {
			Notification value = objectMapper.readValue(jsonMessage, Notification.class);
			 
			notificationRepository.save(value);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
