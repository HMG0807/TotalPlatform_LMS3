package templates.user;

import org.springframework.stereotype.Service;

import com.example.demo.lms.user.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SecurityService {
	
	private final UserService userService;
	
	

}
