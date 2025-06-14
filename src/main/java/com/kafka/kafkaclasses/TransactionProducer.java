package com.kafka.kafkaclasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.dto.Message;

@Service
public class TransactionProducer {
 
	@Autowired
	private KafkaTemplate<String, String>kafkaTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	

	 private final String topic = "transaction-topic";
	 
	    public void send(Message message) throws JsonProcessingException {
	        String jsonMessage = objectMapper.writeValueAsString(message);
	        kafkaTemplate.send(topic, jsonMessage);
	    }
}
