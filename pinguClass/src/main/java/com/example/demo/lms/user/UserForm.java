package com.example.demo.lms.user;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {
	
	@NotEmpty(message = "아이디(ID)를 입력해주세요.")
	private String id;
	
	@NotEmpty(message = "비밀번호를 입력해주세요.")
	private String pw1;
	
	@NotEmpty(message = "비밀번호 확인을 입력해주세요.")
	private String pw2;
	
	@NotEmpty(message = "이름을 입력해주세요.")
	private String name;
	
	@NotEmpty(message = "이메일(ID)를 입력해주세요.")
	private String email1;
	
	@NotEmpty(message = "이메일(ID) 도메인주소를 입력해주세요.")
	private String email2; //도메인
	
	@Length(min = 11, max = 11,  message = "전화번호 11자리를 입력해주세요.")
	@NotEmpty(message = "")
	private String tel;
	
	@NotEmpty(message = "성별을 입력해주세요.")
	private String gender;
	
	@NotEmpty(message = "생년월일을 입력해주세요.")
	private String birth;


}
