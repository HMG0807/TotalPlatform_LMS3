package com.example.demo.lms.mypage;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseForm {

	@ValidFile(message="이미지를 등록해주세요!")
	private MultipartFile mainImg;
	
	@NotEmpty(message="카테고리를 입력해 주세요!")
	private String category;
	
	@NotEmpty(message="제목을 입력해 주세요!")
	private String title;
	
	@NotEmpty(message="내용을 입력해 주세요!")
	private String content;
	
	@NotEmpty(message="목표를 입력해 주세요!")
	private String objective;
	
	@NotNull(message="가격을 입력해 주세요!")
	@Min(value=100, message="가격은 최소한 3자리 이상 입력하세요.")
	private Integer price;
	
}

