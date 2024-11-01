package com.example.demo.lms.mypage;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseForm {

	private String category;
	private String title;
	private String content;
	private String objective;
	private Integer price;
	private MultipartFile bannerImg;
}
