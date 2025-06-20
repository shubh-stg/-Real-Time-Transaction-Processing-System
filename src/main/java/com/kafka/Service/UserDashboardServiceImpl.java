package com.kafka.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kafka.dto.BalanceOverviewDto;
import com.kafka.dto.RecentTransactionDto;
import com.kafka.entity.Transaction;
import com.kafka.entity.User;
import com.kafka.repository.TransactionRepository;
import com.kafka.repository.UserRepository;

@Service
public class UserDashboardServiceImpl implements UserDashboardService {
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public Page<RecentTransactionDto> getRecentTransactions(Long userId, Pageable pageable) {

		Page<Transaction> data = transactionRepository.findBySenderIdOrRecieverIdOrderByTimestampDesc(userId, userId,
				pageable);
		return data.map(trans -> {
			Boolean isSenderId = trans.getSenderId() != null && trans.getSenderId().equals(userId);
			Long counterpartId = isSenderId ? trans.getRecieverId() : trans.getSenderId();
			
	        String counterpartName = "Unknown User";
	        if (counterpartId != null) {
	            Optional<User> counterpart = userRepository.findById(counterpartId);
	            counterpartName = counterpart.map(User::getName).orElse("Unknown User");
	        }

			return new RecentTransactionDto(trans.getTimestamp(), isSenderId ? "Sent" : "Recieved", counterpartName,
					trans.getAmount(), trans.getStatus()

			);
		});

	}

	@Override
	public BalanceOverviewDto getBalanceOverview(Long userId) {
		 double currentBalance = userRepository.findById(userId)
			        .orElseThrow(() -> new RuntimeException("User not found"))
			        .getBalance();

		  Double totalSent = transactionRepository.sumAmountBySender(userId);
		    Double totalReceived = transactionRepository.sumAmountByReceiver(userId);
		    Integer totalTransactions = transactionRepository.countBySenderIdOrReceiverId(userId);
		    Double averageTransaction = transactionRepository.averageAmountByUser(userId);
		    Double largestTransaction = transactionRepository.maxAmountBySenderId(userId);

		    return new BalanceOverviewDto(
		        currentBalance,
		        totalSent != null ? totalSent : 0.0,
		        totalReceived != null ? totalReceived : 0.0,
		        totalTransactions != null ? totalTransactions : 0,
		        averageTransaction != null ? averageTransaction : 0.0,
		        largestTransaction != null ? largestTransaction : 0.0
		    );		
	}

}
