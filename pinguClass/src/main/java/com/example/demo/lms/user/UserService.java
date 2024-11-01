package com.example.demo.lms.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.lms.entity.User;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	
	public User getUser(String id) throws Exception {
		Optional<User> user = this.userRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		else {
			throw new Exception("해당 유저가 존재하지 않습니다");
		}
	}
}
