package com.kafka.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafka.dto.UserDto;
import com.kafka.entity.User;
import com.kafka.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public  List<UserDto> getAllUsers() {
		List<User> all = userRepository.findAll();
		List<UserDto> collect = all.stream().map(user-> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return collect;
		
}

    public User saveUser(UserDto userDto) {
    	if (String.valueOf(userDto.getId()).length() != 10) {
    	    throw new IllegalArgumentException("ID must be a 10-digit number");
    	}
    	User user=modelMapper.map(userDto, User.class);
        return userRepository.save(user);
    }

	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
		
	}
	
}
