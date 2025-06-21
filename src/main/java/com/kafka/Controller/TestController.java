package com.kafka.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

@CrossOrigin(origins ="http://localhost:5173/")
@RestController
@RequestMapping("/api")
public class TestController { 

	
	@Autowired
	private TransactionService transactionService;
	

	@GetMapping("/transaction-history")
	public ResponseEntity<List<Transaction>> getAllTransactions(){
		List<Transaction> allUsers = transactionService.getAllTrans();
		return new ResponseEntity<>(allUsers,HttpStatus.OK);
	} 
	
		
}
