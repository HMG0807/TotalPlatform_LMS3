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
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "report")
@Getter
@Setter
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "report_id")
	private Integer reportId;
	
	private String content;
	@Column(name = "create_date")
	private LocalDateTime createDate; //신고날짜
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(referencedColumnName = "user_id")
	private User reporter; //신고한 유저
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(referencedColumnName = "user_id")
	private User reportee; //신고당한 유저
	
}
