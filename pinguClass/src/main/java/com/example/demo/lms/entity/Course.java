package com.example.demo.lms.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "course")
@Getter
@Setter
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private Integer courseId;
	
	private String title; //제목
	private String content; //내용
	private String objective; //목표
	private Integer price; //가격
	@Column(name = "file_id")
	private Integer fileId; //메인 배너 이미지
	@Column(name = "last_update")
	private LocalDateTime lastUpdate; //수정날짜
	@Column(name = "delete_yn")
	private String deleteYn; //삭제여부
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "inst_id")
	private Instructor instructor;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "course")
    private List<Lecture> lectures; //강의 리스트
	
	@OneToMany(mappedBy = "course")
    private List<Registration> registrations; //수강 강좌 리스트
	
	@OneToMany(mappedBy = "course")
    private List<Review> reviews; //리뷰 리스트
	
	@OneToMany(mappedBy = "course")
    private List<Qna> qnas; //강좌 QnA 리스트
	
	@OneToMany(mappedBy = "course")
    private List<CoursePayment> coursePayments; //강좌 결제 리스트
}









