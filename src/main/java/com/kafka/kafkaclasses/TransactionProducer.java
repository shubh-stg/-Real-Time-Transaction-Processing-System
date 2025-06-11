package com.kafka.kafkaclasses;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.dto.Message;

@Service
public class TransactionProducer {
 
	private KafkaTemplate<String, String>kafkaTemplate;
	private final ObjectMapper objectMapper;
	
	public TransactionProducer(KafkaTemplate<String, String>kafkaTemplate,ObjectMapper objectMapper) {
		this.kafkaTemplate=kafkaTemplate;
		this.objectMapper=new ObjectMapper();
	}
	 private final String topic = "transaction-topic";
	 
	    public void send(Message message) throws JsonProcessingException {
	        String jsonMessage = objectMapper.writeValueAsString(message);
	        kafkaTemplate.send(topic, jsonMessage);
	    }
}
