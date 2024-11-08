package com.example.demo.lms.mypage;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.lms.entity.Category;
import com.example.demo.lms.entity.CoursePayment;
import com.example.demo.lms.entity.File;
import com.example.demo.lms.entity.Instructor;
import com.example.demo.lms.entity.Lecture;
import com.example.demo.lms.entity.Qna;
import com.example.demo.lms.entity.Registration;
import com.example.demo.lms.entity.Review;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseDTO {

	private Integer courseId;
	private String title; //제목
	private String content; //내용
	private String objective; //목표
	private Integer price; //가격
	private LocalDateTime lastUpdate; //수정날짜
	private String deleteYn; //삭제여부
	
	private Instructor instructor;
	private Category category;
	
	private File file; //강좌 메인 이미지
	
    private List<Lecture> lectures; //강의 리스트
    private List<Registration> registrations; //수강 강좌 리스트
    private List<Review> reviews; //리뷰 리스트
    private List<Qna> qnas; //강좌 QnA 리스트
    private List<CoursePayment> coursePayments; //강좌 결제 리스트
}
