package com.kafka.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.Service.UserDashboardService;
import com.kafka.dto.BalanceOverviewDto;
import com.kafka.dto.Message;
import com.kafka.dto.RecentTransactionDto;
import com.kafka.kafkaclasses.TransactionProducer;

@CrossOrigin(origins ="http://localhost:5173/")
@RestController
@RequestMapping("/api/user-analytics")
public class UserDashboardController {
	
	@Autowired
	private UserDashboardService userDashboardService;
	@Autowired
	private TransactionProducer transactionProducer;
	
	
	@GetMapping("/recent/{userId}")
	public ResponseEntity<Page<RecentTransactionDto>> getRecentTransactions(
	    @PathVariable Long userId,
	    @PageableDefault(size = 10) Pageable pageable){
		
    Page<RecentTransactionDto> transactions = userDashboardService.getRecentTransactions(userId, pageable);
    return ResponseEntity.ok(transactions);
	    }
	
	@GetMapping("/balance-overview/{userId}")
	public ResponseEntity<BalanceOverviewDto> getBalanceOverview(@PathVariable Long userId) {
	    return ResponseEntity.ok(userDashboardService.getBalanceOverview(userId));
	}
	
	
	@PostMapping("/publish")
	public ResponseEntity<String>publish(@RequestBody Message message) throws JsonProcessingException{
		transactionProducer.send(message);
		return ResponseEntity.ok("Transaction sent to kafka successfully");
		
	}
}
