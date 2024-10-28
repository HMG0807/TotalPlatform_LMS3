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
@Table(name = "notice_file")
@Getter
@Setter
public class NoticeFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nf_id")
	private Integer nfId;
	
	@Column(name = "file_id")
	private Integer fileId; //파일 번호
	
	@ManyToOne
	@JoinColumn(name = "notice_id")
	private Notice notice;
}
