package com.example.demo.totalPlatform;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.lms.entity.User;
import com.example.demo.lms.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TotalUserService {

	private final UserRepository userRepository;

	
	public User returncreate(TotalUser totalUser) {
		
		User user = new User();
		
		
		user.setId(totalUser.getId());
		user.setEmail(totalUser.getEmail());
		user.setPw(totalUser.getPw());
		user.setName(totalUser.getName());
		user.setTel(totalUser.getTel());
		user.setBirth(totalUser.getBirth());
		user.setGender(totalUser.getGender());
		user.setBannedYn(totalUser.getBannedYn());
		user.setSignoutYn(totalUser.getSignoutYn());
		user.setCreateDate(LocalDateTime.now());
		
//		this.userRepository.save(user);
		
		return user;
	}
}
