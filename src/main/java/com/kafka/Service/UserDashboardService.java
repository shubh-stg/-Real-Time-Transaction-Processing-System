package com.kafka.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kafka.dto.BalanceOverviewDto;
import com.kafka.dto.RecentTransactionDto;

public interface UserDashboardService {
	 Page<RecentTransactionDto> getRecentTransactions(Long userId, Pageable pageable);
	 
	 BalanceOverviewDto getBalanceOverview(Long userId);
}
