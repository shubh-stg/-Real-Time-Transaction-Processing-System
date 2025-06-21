package com.kafka.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.Service.AnalyticsService;
import com.kafka.Service.UserService;
import com.kafka.dto.BasicAdminAnalytics;
import com.kafka.dto.DailyTransactionDTO;
import com.kafka.dto.StatusRatioDTO;
import com.kafka.dto.TopUserDTO;
import com.kafka.dto.UserDto;

@CrossOrigin(origins ="http://localhost:5173/")
@RestController
@RequestMapping("api/analytics")
public class AnalyticsController {
	
	@Autowired
	private AnalyticsService analyticsService;
	
	@Autowired
	private UserService userService;
	
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
    
	@GetMapping("/get-users")
 public ResponseEntity<List<UserDto>> getAllUsers(){
	 List<UserDto> allUsers = userService.getAllUsers();
	return new ResponseEntity<>(allUsers,HttpStatus.OK);
	 
 }
	@DeleteMapping("/delete-user")
	public ResponseEntity<String>deleteUser(@PathVariable Long userId){
		userService.deleteUser(userId);
		return ResponseEntity.ok("User Deleted Successfully");
	}

}
