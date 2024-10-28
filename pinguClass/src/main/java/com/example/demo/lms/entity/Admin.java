package com.example.demo.lms.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "admin")
@Getter
@Setter
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	private Integer adminId;
	
	@Column(name = "admin_code")
	private String adminCode;
	
	@OneToMany(mappedBy = "admin")
    private List<CsAnswer> csAnswers;
	
	@OneToMany(mappedBy = "admin")
    private List<Notice> notices;
}
