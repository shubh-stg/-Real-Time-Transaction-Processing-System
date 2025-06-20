package com.kafka.Service;

import java.util.List;

import com.kafka.dto.BasicAdminAnalytics;
import com.kafka.dto.DailyTransactionDTO;
import com.kafka.dto.StatusRatioDTO;
import com.kafka.dto.TopUserDTO;

public interface AnalyticsService {
	
	
	
	List<StatusRatioDTO> getTransactionStatusRatio();

	List<DailyTransactionDTO> getDailyTransactionStats();
	
	List<TopUserDTO> getTopUsersByVolume();

	BasicAdminAnalytics basicAdminAnalytics();
}
