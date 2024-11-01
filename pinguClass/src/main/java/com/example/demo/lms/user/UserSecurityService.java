package com.example.demo.lms.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.lms.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> _buyuser = this.userRepository.findById(username);
		if(_buyuser.isEmpty()) {
			throw new UsernameNotFoundException("사용자가 존재하지 않습니다");
		}
		
		User user = _buyuser.get();
		List<GrantedAuthority> auth = new ArrayList<>();
//		if("admin".equals(username)) {
//			auth.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
//		}else if("saleuser".equals(user.getRole())){
//			auth.add(new SimpleGrantedAuthority(UserRole.SALEUSER.getValue()));
//		}
//		else {
//			auth.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
//		}
		return new org.springframework.security.core.userdetails.User(user.getId(), user.getPw(), auth);
	}
	
	



}
