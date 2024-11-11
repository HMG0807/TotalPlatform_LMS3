package com.example.demo.lms.entity;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "lecture")
@Getter
@Setter
public class Lecture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lecture_id")
	private Integer lectureId;
	
	private String title; //제목
	@Column(name = "lecture_order")
	private Integer lectureOrder; //강의 순서
	private String objective; //목표
	@Column(name = "file_id")
	private Integer fileId; //동영상 파일
	@Column(name = "last_update")
	private LocalDateTime lastUpdate; //수정날짜
	@Column(name = "delete_yn")
	private String deleteYn; //삭제여부
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	@OneToMany(mappedBy = "lecture")
    private List<LectureIndex> lectureIndexs; //목차 리스트
	
}
