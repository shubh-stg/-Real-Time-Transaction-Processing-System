package com.kafka.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kafka.dto.UserDto;
import com.kafka.entity.Role;
import com.kafka.entity.User;
import com.kafka.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public  List<UserDto> getAllUsers() {
		List<User> all = userRepository.findAll();
		List<UserDto> collect = all.stream().map(user-> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return collect;
		
}

    public User saveUser(UserDto userDto) {
    	if (String.valueOf(userDto.getId()).length() != 10) {
    	    throw new IllegalArgumentException("ID must be a 10-digit number");
    	}
    	User user =new User();
    	user.setId(userDto.getId());
    	user.setName(userDto.getName());
    	user.setBalance(userDto.getBalance());
    	user.setUsername(userDto.getUsername());
    	user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    	user.setRole(Role.ROLE_USER);
    	return userRepository.save(user);
        
    }

	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
		
	}
	public Boolean isUserExist(String username) {
		return userRepository.existsByUsername(username);
	}
}
