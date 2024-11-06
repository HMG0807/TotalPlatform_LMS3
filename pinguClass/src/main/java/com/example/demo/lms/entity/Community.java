package com.example.demo.lms.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
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
@Table(name = "community")
@Getter
@Setter
public class Community {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cm_id")
	private Integer cmId;
	
	private String title;
	private String content;
	@Column(name = "last_update")
	private LocalDateTime lastUpdate; //최근업데이트 날짜
	@Column(name = "delete_yn")
	private String deleteYn; //삭제여부
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "community")
    private List<CommunityFile> communityFiles;
	
	@OneToMany(mappedBy = "community")
    private List<CommunityLike> communityLikes;
	
	@OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<CommunityComment> communityComments;

}
