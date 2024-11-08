package com.example.demo.lms.mypage;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LectureForm {

	@ValidFile(message="영상을 등록해주세요!")
	private MultipartFile lectureVideo;
	
	@NotEmpty(message="제목을 입력해 주세요!")
	private String title;
	
	@NotEmpty(message="목표를 입력해 주세요!")
	private String objective;
	
	private String courseId; //등록할 강좌
	
}
