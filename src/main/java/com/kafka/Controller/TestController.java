package com.kafka.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.Service.TransactionService;
import com.kafka.Service.UserService;
import com.kafka.dto.Message;
import com.kafka.dto.UserDto;
import com.kafka.entity.Transaction;
import com.kafka.kafkaclasses.TransactionProducer;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class TestController {
	
	@Autowired
	private TransactionProducer transactionProducer;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/get")
 public ResponseEntity<List<UserDto>> getAllUsers(){
	 List<UserDto> allUsers = userService.getAllUsers();
	return new ResponseEntity<>(allUsers,HttpStatus.OK);
	 
 }
	
	@PostMapping("/post")
	public ResponseEntity<String>createUser(@Valid @RequestBody UserDto user){
	userService.saveUser(user);
		return ResponseEntity.ok("UserCreated Successfully");
	}
	@PostMapping("/publish")
	public ResponseEntity<String>publish(@RequestBody Message message) throws JsonProcessingException{
		transactionProducer.send(message);
		return ResponseEntity.ok("Transaction sent to kafka successfully");
		
	}
	@GetMapping("/transaction-history")
	public ResponseEntity<List<Transaction>> getAllTransactions(){
		List<Transaction> allUsers = transactionService.getAllTrans();
		return new ResponseEntity<>(allUsers,HttpStatus.OK);
	} 
	
		
}
