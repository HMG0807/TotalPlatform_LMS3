package com.example.demo.lms.mypage;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
	
	@NotEmpty(message="목차 제목을 입력해 주세요!")
	private List<String> lectureIndexTitle;
	
	@NotNull(message="목차 시간을 모두 선택해 주세요!")
	private List<Integer> indexHour;
	@NotNull(message="목차 시간을 모두 선택해 주세요!")
	private List<Integer> indexMinute;
	@NotNull(message="목차 시간을 모두 선택해 주세요!")
	private List<Integer> indexSecond;
	
}
