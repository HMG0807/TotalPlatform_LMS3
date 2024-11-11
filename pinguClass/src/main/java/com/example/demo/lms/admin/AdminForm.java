package com.example.demo.lms.admin;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminForm {

	@NotEmpty(message="관리자 코드를 입력해 주세요!")
	private String adminCode;
}
