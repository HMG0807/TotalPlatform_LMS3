package com.example.demo.lms.main;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.lms.Authuser.Authuser;
import com.example.demo.lms.Authuser.AuthuserService;
import com.example.demo.lms.LoginCheck.LoginCheck;
import com.example.demo.lms.LoginCheck.undefinedUserException;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.user.UserRepository;
import com.example.demo.totalPlatform.TotalUser;
import com.example.demo.totalPlatform.TotalUserRepository;
import com.example.demo.totalPlatform.TotalUserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	private final AuthuserService authuserService;
	private final UserRepository userRepository;
	private final TotalUserRepository totalUserRepository;
	private final TotalUserService totalUserService;
	
	
	@GetMapping("/main")
	public String mainpage(@Authuser User user) {
		if(user.getId().equals("null")) {
			return "main/main";
		}
		
		if(userRepository.findById(user.getId()).isEmpty()) {
			return "user/confirm";
		}
		
		return "main/main";
		
	}
	
	@GetMapping("/")
	public String in1dex(@Authuser User user)  {
		
		
		if(user.getId().equals("null")) {
			return "main/main";
		}
		
		if(userRepository.findById(user.getId()).isEmpty()) {
			return "user/confirm";
		}
		
		return "main/main";
	}
	
	@PostMapping("/confirm")
	public ResponseEntity<String> agreePage(HttpServletRequest request) {
		String jwtToken = request.getHeader("Authorization");
		System.out.println(jwtToken);
		ResponseEntity<String> user = authuserService.getNameWithHeader(jwtToken).block();
    	List<String> listHeader = user.getHeaders().get("Principal");
    
    	
    	String userId =  listHeader.get(0);
    	TotalUser TotalUser =  totalUserRepository.findById(userId);
		User userbyTotalUser = totalUserService.returncreate(TotalUser);
		userRepository.save(userbyTotalUser);
		
		return ResponseEntity.ok("성공");
	}


}
