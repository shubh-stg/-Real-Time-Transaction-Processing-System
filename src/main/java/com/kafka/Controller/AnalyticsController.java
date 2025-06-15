package com.kafka.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.Service.AnalyticsService;
import com.kafka.dto.DailyTransactionDTO;
import com.kafka.dto.StatusRatioDTO;
import com.kafka.dto.TopUserDTO;

@RestController
@RequestMapping("api/analytics")
public class AnalyticsController {
	
	@Autowired
	private AnalyticsService analyticsService;
	
	@GetMapping("/user-count")
	public long getUserCount() {
	    return analyticsService.getTotalUserCount();
	}

	@GetMapping("/total-volume")
	public float getTotalVolume() {
	    return analyticsService.getTotalTransactionVolume();
	}

	@GetMapping("/transaction-count")
	public long getTransactionCount() {
	    return analyticsService.getTotalTransactionCount();
	}
	
    @GetMapping("/status-ratio")
    public ResponseEntity<List<StatusRatioDTO>> getTransactionStatusRatio() {
        return ResponseEntity.ok(analyticsService.getTransactionStatusRatio());
    }
    
    @GetMapping("/daily-volume")
    public ResponseEntity<List<DailyTransactionDTO>> getDailyTransactionVolume() {
        return ResponseEntity.ok(analyticsService.getDailyTransactionStats());
    }
    @GetMapping("/top-users")
    public ResponseEntity<List<TopUserDTO>> getTopUsersByVolume() {
        return ResponseEntity.ok(analyticsService.getTopUsersByVolume());
    }


}
