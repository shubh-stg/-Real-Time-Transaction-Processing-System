package com.kafka.Service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kafka.entity.User;
import com.kafka.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
		
		  return new org.springframework.security.core.userdetails.User(
	                user.getUsername(), user.getPassword(),
	                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString()))
	        );
	}

}
