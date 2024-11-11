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
	
	
	// 11/11 이순 통합로그인으로 수정!!!! 바꾸면 안돼요!!
	public User getUser(Integer userId) throws Exception {

		Optional<User> user = this.userRepository.findById(userId);



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

	
	
	/*강좌 QnA 관리에서 질문 등록 하기 위해 추가 ID로 사용자 객체를 조회하기  위해 - 남동현  */
	 public User findById(Integer userId) {
	        return userRepository.findById(userId).orElse(null);  // ID로 사용자 조회
	    }
	
	


}
