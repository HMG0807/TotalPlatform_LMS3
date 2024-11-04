package com.example.demo.lms.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "qna_answer")
@Getter
@Setter
public class QnaAnswer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "answer_id")
	private Integer answerId;
	
	private String title;
	private String content;
	@Column(name = "last_update")
	private LocalDateTime lastUpdate; //최근업데이트 날짜
	@Column(name = "delete_yn")
	private String deleteYn; //삭제여부
	
	@OneToOne
	@JoinColumn(name="qna_id")
	private Qna qna;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "inst_id")
	private Instructor instructor;
}
