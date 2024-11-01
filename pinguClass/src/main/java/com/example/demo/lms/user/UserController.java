package com.example.demo.lms.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.lms.entity.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;
	
	// 로그인창
	@GetMapping("/login")
    public String login() {
        return "user/login";
    } 
		
	
	
	
	// 회원가입창
	@GetMapping("/sign")
	public String userCreate(UserForm userForm) {
		
		return "user/signPage";
	}
	
	
	// 회원가입하기
	@PostMapping("/sign")
	public String signup(@Valid UserForm userForm, BindingResult bindingResult,
			HttpServletRequest request) throws Exception {
		System.out.println(userForm.getId());
		System.out.println(userForm.getPw1());
		System.out.println(userForm.getPw2());
		System.out.println(userForm.getName());
		System.out.println(userForm.getEmail1());
		System.out.println(userForm.getEmail2());
		System.out.println(userForm.getTel());
		System.out.println(userForm.getGender());
		System.out.println(userForm.getBirth().replace("-", ""));
		if(bindingResult.hasErrors()) {
			
			System.out.println("여기탈거임");
			return "user/signPage";
		}
		
		//비밀번호=!비밀번호확인
		if(!userForm.getPw1().equals(userForm.getPw2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect",
					"패스워드가 일치하지 않습니다.");
			return "user/signPage"; //에러가 있는경우 반환할 뷰
		}
		
		try {
			this.userService.returncreate(
					userForm.getId(),
					userForm.getEmail1(),
					userForm.getEmail2(),
					userForm.getPw1(),
					userForm.getName(),
					userForm.getTel(),
					userForm.getBirth(),
					userForm.getGender());
			
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
			return "user/signPage"; //에러가 있는경우 반환할 뷰
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return "redirect:/user/login";
	}
	
	


}
