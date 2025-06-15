package com.kafka.Service;

import java.util.List;

import com.kafka.dto.DailyTransactionDTO;
import com.kafka.dto.StatusRatioDTO;
import com.kafka.dto.TopUserDTO;

public interface AnalyticsService {
	
	long getTotalUserCount();
	
	long getTotalTransactionCount();
	
	float getTotalTransactionVolume();
	
	List<StatusRatioDTO> getTransactionStatusRatio();

	List<DailyTransactionDTO> getDailyTransactionStats();
	
	List<TopUserDTO> getTopUsersByVolume();
}
