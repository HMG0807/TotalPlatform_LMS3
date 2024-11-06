package com.example.demo.lms.admin;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerForm {

	@NotEmpty(message="제목이 없습니다")
	@Size(max=50)
	private String title;
	
	@NotEmpty(message="내용이 없습니다")
	private String contents;
	
	private Integer questionId;
	
}
