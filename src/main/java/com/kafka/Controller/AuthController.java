package com.kafka.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.Service.JwtService;
import com.kafka.Service.UserService;
import com.kafka.dto.JwtResponse;
import com.kafka.dto.UserDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/register")
	public ResponseEntity<String> Register(@Valid @RequestBody UserDto userDto){
		
			if(userService.isUserExist(userDto.getUsername())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("UserName already Exists");
			}
			 userService.saveUser(userDto);
			return ResponseEntity.ok("User Successfully Created");

	}
	
	@PostMapping("/login")
	public ResponseEntity<?> Login(@RequestBody UserDto userDto){
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
		
		String token= jwtService.generateToken(userDto.getUsername());
		
		
		
		return ResponseEntity.ok(new JwtResponse(token));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
		}
		
	}
	
}
