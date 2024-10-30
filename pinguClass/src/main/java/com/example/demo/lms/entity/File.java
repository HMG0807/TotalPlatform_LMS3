package com.example.demo.lms.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "file")
@Getter
@Setter
public class File {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "file_id")
	private Integer fileId;
	
	@Column(name = "file_name")
	private String fileName; //파일명
	@Column(name = "file_url")
	private String fileUrl; //파일경로
	@Column(name = "file_type")
	private String fileType; //확장자
	@Column(name = "last_update")
	private LocalDateTime lastUpdate; //최근업데이트 날짜
	@Column(name = "delete_yn")
	private String deleteYn; //삭제여부
}
