package com.kafka.kafkaclasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.dto.NotificationDto;

@Service
public class NotificationProducer {

	 @Autowired
		private KafkaTemplate<String, String>kafkaTemplate;
	 @Autowired
		private  ObjectMapper objectMapper;
		
		public NotificationProducer(KafkaTemplate<String, String>kafkaTemplate,ObjectMapper objectMapper) {
			this.kafkaTemplate=kafkaTemplate;
			this.objectMapper=new ObjectMapper();
		}
		 private final String topic = "notification-topic";
		 
		    public void send(NotificationDto dto) throws JsonProcessingException {
		        String jsonMessage = objectMapper.writeValueAsString(dto);
		        kafkaTemplate.send(topic, jsonMessage);
		    }
}
