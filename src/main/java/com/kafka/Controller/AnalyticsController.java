package com.kafka.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.Service.AnalyticsService;
import com.kafka.dto.BasicAdminAnalytics;
import com.kafka.dto.DailyTransactionDTO;
import com.kafka.dto.StatusRatioDTO;
import com.kafka.dto.TopUserDTO;

@CrossOrigin(origins ="http://localhost:5173/")
@RestController
@RequestMapping("api/analytics")
public class AnalyticsController {
	
	@Autowired
	private AnalyticsService analyticsService;
	
	@GetMapping("/admin-analytics")
	public ResponseEntity<BasicAdminAnalytics> getAnalytics(){
		return ResponseEntity.ok(analyticsService.basicAdminAnalytics());
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
