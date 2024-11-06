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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.lms.entity.User;



import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("책임을 물을것이야! : 앙!" + username);
		System.out.println("test");

			Optional<User> _buyuser = this.userRepository.loginId(username);
			System.out.println("test2");
			if(_buyuser.isEmpty()) {
				System.out.println("test3");
				throw new UsernameNotFoundException("사용자가 존재하지 않습니다");
			}
			
			System.out.println("test4");
			User user = _buyuser.get();
			List<GrantedAuthority> auth = new ArrayList<>();
			auth.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
//			if("admin".equals(username)) {
//				auth.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
//			}else {
//				auth.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
//			}
			//return new User(siteUser.getUsername(), siteUser.getPassword(), auth);
			
			// 엔티티명과 시큐리티가 겹쳐서 임포트를 짱박음
			return new org.springframework.security.core.userdetails.User(user.getId(), user.getPw(), auth);

		
		

	}
	
	
	



}
