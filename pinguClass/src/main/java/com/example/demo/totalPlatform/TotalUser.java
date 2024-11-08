package com.example.demo.totalPlatform;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.CommunityComment;
import com.example.demo.lms.entity.CommunityLike;
import com.example.demo.lms.entity.Coupon;
import com.example.demo.lms.entity.CoursePayment;
import com.example.demo.lms.entity.CsQuestion;
import com.example.demo.lms.entity.Instructor;
import com.example.demo.lms.entity.Qna;
import com.example.demo.lms.entity.Registration;
import com.example.demo.lms.entity.Report;
import com.example.demo.lms.entity.Review;
import com.example.demo.lms.entity.SubPayment;
import com.example.demo.lms.entity.Subscription;
import com.example.demo.lms.entity.UserCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class TotalUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	
	private String id;
	private String pw;
	private String email;
	private String name;
	private String tel;
	private String birth;
	private String gender;
	@Column(name = "create_date")
	private LocalDateTime createDate; //회원가입일
	
	@Column(name = "banned_yn")
	private String bannedYn; //정지여부
	
	@Column(name = "signout_yn")
	private String signoutYn; //회원탈퇴여부
	
	@Column(name = "refresh_token")
	private String refreshToken; //로그인 토큰
	
	private String role; //로그인 권한
	
	
	
	
	
}
