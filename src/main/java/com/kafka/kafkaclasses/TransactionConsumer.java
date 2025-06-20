package com.kafka.kafkaclasses;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.Exception.InsufficientBalanceException;
import com.kafka.dto.Message;
import com.kafka.dto.NotificationDto;
import com.kafka.entity.NotificationType;
import com.kafka.entity.TransactStatus;
import com.kafka.entity.Transaction;
import com.kafka.entity.User;
import com.kafka.repository.TransactionRepository;
import com.kafka.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionConsumer {

    private static final Logger logger = LoggerFactory.getLogger(TransactionConsumer.class);
    @Autowired
    private  ObjectMapper objectMapper ;

    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    

    
    @Autowired
    private NotificationProducer notificationProducer;

    @Transactional
    @KafkaListener(topics = "transaction-topic", groupId = "transaction-group" ,containerFactory = "transactionKafkaListenerFactory")
    public void consume(String jsonmessage) {
        TransactStatus status;
        Message message = null;

        try {
            message = objectMapper.readValue(jsonmessage, Message.class);

            User sender = userRepository.findByIdForUpdate(message.getSenderId())
                    .orElseThrow(() -> new IllegalArgumentException("Sender not found"));

            User receiver = userRepository.findByIdForUpdate(message.getRecieverId())
                    .orElseThrow(() -> new IllegalArgumentException("Receiver not found"));

            if (sender.getBalance() < message.getAmount()) {
                throw new InsufficientBalanceException("Insufficient balance for user ID: " + sender.getId());
            }

            sender.setBalance(sender.getBalance() - message.getAmount());
            receiver.setBalance(receiver.getBalance() + message.getAmount());

            userRepository.save(sender);
            
            
            if (sender.getBalance() < 1000) {
                NotificationDto warning = new NotificationDto();
                warning.setUserId(sender.getId());
                warning.setMessage("Warning: Your account balance is below ₹1000.");
                warning.setType(NotificationType.WARNING);
                warning.setTimestamp(LocalDateTime.now());
                notificationProducer.send(warning);
            }
            
            
            
            userRepository.save(receiver);

            status = TransactStatus.SUCCESS;
            
          sendNotification(message.getSenderId(),"You successfully sent ₹" + message.getAmount() + " to user " + receiver.getName(),NotificationType.SUCCESS);
          sendNotification(message.getRecieverId(),"You received ₹" + message.getAmount() + " from user " + sender.getName(),NotificationType.SUCCESS);
//            logger.info("Sender: ID={}, Name={}", sender.getId(), sender.getName());
//            logger.info("Receiver: ID={}, Name={}", receiver.getId(), receiver.getName());

        } catch (Exception e) {
            status = TransactStatus.FAILED;
            
            logger.error("Error while consuming message", e);
            
            try {
				sendNotification(message.getSenderId(), "Transaction failed: " + e.getMessage() , NotificationType.FAILURE);
			} catch (JsonProcessingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }

        try {
            if (message != null) {
                Transaction record = new Transaction();
                record.setSenderId(message.getSenderId());
                record.setRecieverId(message.getRecieverId());
                record.setAmount(message.getAmount());
                record.setTimestamp(LocalDateTime.now());
                record.setStatus(status);
                transactionRepository.save(record);
            }
        } catch (Exception ex) {
            logger.error("Failed to persist transaction record: {}", ex.getMessage(), ex);
        }
    }

	private void sendNotification(Long Id, String content, NotificationType type) throws JsonProcessingException {
		
		NotificationDto notification=new NotificationDto();
		notification.setUserId(Id);
		notification.setMessage(content);
		notification.setType(type);
		notification.setTimestamp(LocalDateTime.now());
		
		notificationProducer.send(notification);
	}
}

