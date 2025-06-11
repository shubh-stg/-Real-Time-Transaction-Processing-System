package com.kafka.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafka.entity.Transaction;

import com.kafka.repository.TransactionRepository;

@Service
public class TransactionService {
  
	@Autowired
	private TransactionRepository transactionRepository;
	
	public List<Transaction> getAllTrans() {
        return transactionRepository.findAll();
    }
}
