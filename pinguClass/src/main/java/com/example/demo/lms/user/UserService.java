package com.example.demo.lms.user;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.lms.entity.User;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public User getUser(String id) throws Exception {
		Optional<User> user = this.userRepository.findByName(id);
		if(user.isPresent()) {
			return user.get();
		}
		else {
			throw new Exception("해당 유저가 존재하지 않습니다");
		}
	}
	

	// 회원가입 정보 받기
	public User returncreate(String id, String email1, String email2, String pw, String name, String tel, String gender,
			String birth) {
		
		User setUser = new User();
		setUser.setId(id);
		setUser.setEmail(email1+'@'+email2);
		setUser.setPw(passwordEncoder.encode(pw));
		setUser.setName(name);
		setUser.setTel(tel);
		setUser.setBirth(birth.replace("-", ""));
		setUser.setGender(gender);
		setUser.setBannedYn("n");
		setUser.setSignoutYn("n");
		setUser.setCreateDate(LocalDateTime.now());
		
		this.userRepository.save(setUser);
		
		return setUser;
	}
	
	public User loginId() {
		User user = new User();
		user.getId();
		
		return user;
		
	}
	
	public User loginPw() {
		User user = new User();
		user.getPw();
		
		return user;
		
	}
	
//  ID값으로 해당되는 유저를 찾는 메서드
	public User getUserId(String id) throws UserException {
		Optional<User> userId = this.userRepository.findById(id);
		if(userId.isPresent()) {
			return userId.get();
		}
		else {
			throw new UserException("해당 유저가 존재하지 않습니다");
		}
	}
	

	


}
