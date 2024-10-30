package com.example.demo.lms.entity;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notice")
@Getter
@Setter
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notice_id")
	private Integer noticeId;
	
	private String title;
	private String content;
	@Column(name = "last_update")
	private LocalDateTime lastUpdate; //최근업데이트 날짜
	@Column(name = "delete_yn")
	private String deleteYn; //삭제여부
	
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;
	
	@OneToMany(mappedBy = "notice")
    private List<NoticeFile> noticeFiles;
}
