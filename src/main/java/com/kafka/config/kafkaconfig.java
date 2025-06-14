package com.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@Configuration
public class kafkaconfig {
	
	@Bean
	public NewTopic topicCreation() {
		return new NewTopic("transaction-topic", 1, (short)1);
		
	}
	
	@Bean
	public NewTopic topicCreator() {
		return new NewTopic("notification-topic", 1, (short)1);
	}
	@Bean
	public ProducerFactory<String, String> producerFactory(){
		Map<String,Object> props=new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaProducerFactory<>(props);
		
	}
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
	}
	

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
            .registerModule(new JavaTimeModule());
            
    }
	 
}
