package com.kafka.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafka.dto.BasicAdminAnalytics;
import com.kafka.dto.DailyTransactionDTO;
import com.kafka.dto.StatusRatioDTO;
import com.kafka.dto.TopUserDTO;
import com.kafka.repository.TransactionRepository;
import com.kafka.repository.UserRepository;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	


	@Override
	public List<StatusRatioDTO> getTransactionStatusRatio() {
		
		List<Object[]> result = transactionRepository.countTransactionForEachStatus();
		List<StatusRatioDTO> dtoList = result.stream()
			    .map(row -> new StatusRatioDTO(row[0].toString(), (Long) row[1]))
			    .collect(Collectors.toList());

		return dtoList;
	}

	@Override
	public List<DailyTransactionDTO> getDailyTransactionStats() {
		
		LocalDateTime fromDay = LocalDateTime.now().minusDays(7);
		List<Object[]> sumTransactionAmountPerDay = transactionRepository.sumTransactionAmountPerDay(fromDay);
		
		List<DailyTransactionDTO> collect = sumTransactionAmountPerDay.stream().map(obj-> new DailyTransactionDTO(obj[0].toString(), ((Double) obj[1]).floatValue())).collect(Collectors.toList());
		return collect ;
	}

	@Override
	public List<TopUserDTO> getTopUsersByVolume() {
		 List<Object[]> result = transactionRepository.findTopUsersByTransactionVolume();

		    return result.stream()
		        .map(row -> new TopUserDTO(
		            (Long) row[0],
		            	row[1].toString(),
		            ((Double) row[2])
 
		            
		        ))
		        .collect(Collectors.toList());
	}

	@Override
	public BasicAdminAnalytics basicAdminAnalytics() {
		long count =userRepository.count();
		long transac_count=transactionRepository.count();
		 Double sumOfSuccessfulTransactionAmount = transactionRepository.sumOfSuccessfulTransactionAmount();
		 
		return new BasicAdminAnalytics(count, transac_count,sumOfSuccessfulTransactionAmount != null ?sumOfSuccessfulTransactionAmount:0.0);
	}

}
