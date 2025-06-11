package com.kafka.kafkaclasses;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.Exception.InsufficientBalanceException;
import com.kafka.dto.Message;
import com.kafka.entity.TransactStatus;
import com.kafka.entity.Transaction;
import com.kafka.entity.User;
import com.kafka.repository.TransactionRepository;
import com.kafka.repository.UserRepository;

@Service
public class TransactionConsumer {

    private static final Logger logger = LoggerFactory.getLogger(TransactionConsumer.class);

    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @KafkaListener(topics = "transaction-topic", groupId = "transaction-group")
    public void consume(String jsonmessage) {
    	TransactStatus status;
        try {
           
            Message message = objectMapper.readValue(jsonmessage, Message.class);
            
            Optional<User> senderOpt = userRepository.findById(message.getSenderId());
            Optional<User> recieverOpt = userRepository.findById(message.getRecieverId());
            
            if(senderOpt.isEmpty() || recieverOpt.isEmpty()) {
            	throw new IllegalArgumentException("Sender or receiver not found");
            }
            User sender = senderOpt.get();
            User reciever=recieverOpt.get();
            
            if(sender.getBalance()<message.getAmount()) {
            	throw new InsufficientBalanceException("Insufficient  balance for user Id : "+ sender.getId());
            }
            
            sender.setBalance(sender.getBalance() - message.getAmount());
            reciever.setBalance(reciever.getBalance() + message.getAmount());
            
            userRepository.save(sender);
            userRepository.save(reciever);
            
          status=TransactStatus.SUCCESS;
            
        } catch (Exception e) {
        	status=TransactStatus.FAILED;
            logger.error("Error while consuming message", e);
        }
        
        try {
            Message message = objectMapper.readValue(jsonmessage, Message.class);
            Transaction  record = new Transaction();
            record.setSenderId(message.getSenderId());
            record.setRecieverId(message.getRecieverId());
            
            record.setAmount(message.getAmount());
            record.setTimestamp(LocalDateTime.now());
            record.setStatus(status);
            transactionRepository.save(record);
        } catch (Exception ex) {
            logger.error("Failed to persist transaction record: {}", ex.getMessage(), ex);
        }
        
    }
}

