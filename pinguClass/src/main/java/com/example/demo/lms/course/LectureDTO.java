package com.example.demo.lms.course;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.lms.entity.Course;
import com.example.demo.lms.entity.File;
import com.example.demo.lms.entity.LectureIndex;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureDTO {

	private Integer lectureId;
	private String title; //제목
	private Integer lectureOrder; //강의 순서
	private String objective; //목표
	private File file; //동영상 파일
	private LocalDateTime lastUpdate; //수정날짜
	private String deleteYn; //삭제여부
	private Course course;
	
	private List<LectureIndex> lectureIndexs; //목차 리스트
}
