package com.example.demo.lms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lecture_index")
@Getter
@Setter
public class LectureIndex {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "index_id")
	private Integer indexId;
	
	private String title; //목차 제목
	private Integer time; //목차 시간 : 초단위 Integer
	
	@ManyToOne
	@JoinColumn(name = "lecture_id")
	private Lecture lecture;
}
