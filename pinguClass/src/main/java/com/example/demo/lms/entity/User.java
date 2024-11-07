package com.example.demo.lms.entity;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name="id")
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
	
	@OneToMany(mappedBy = "user")
    private List<CsQuestion> questions; //1:1문의글 리스트
	
	@JsonManagedReference
	@OneToMany(mappedBy = "user")
    private List<Community> communitys; //커뮤니티 작성글 리스트
	
	@JsonManagedReference
	@OneToMany(mappedBy = "reporter")
    private List<Report> reporters; //신고한 리스트
	
	@JsonManagedReference
	@OneToMany(mappedBy = "reportee")
    private List<Report> reportees; //신고 당한 리스트
	
	@OneToMany(mappedBy = "user")
    private List<CommunityLike> communityLikes; //커뮤니티 좋아요 리스트
	
	@JsonManagedReference
	@OneToMany(mappedBy = "user")
    private List<CommunityComment> communityComments; //커뮤니티 댓글 리스트
	
	@OneToMany(mappedBy = "user")
	private List<SubPayment> subPayments; //구독 결제 리스트
	
	@OneToOne(mappedBy = "user")
	private Subscription subscription; //현재 구독 상태
	
	@JsonManagedReference
	@OneToMany(mappedBy = "user")
	private List<Coupon> coupons; //쿠폰 코드 리스트
	
	@OneToMany(mappedBy = "user")
    private List<UserCategory> userCategorys; //유저카테고리 리스트
	
	@OneToMany(mappedBy = "user")
    private List<Registration> registrations; //수강 강좌 리스트
	
	@OneToMany(mappedBy = "user")
    private List<Review> reviews; //리뷰 리스트
	
	@OneToMany(mappedBy = "user")
    private List<Qna> qnas; //강좌 QnA 리스트
	
	@OneToMany(mappedBy = "user")
    private List<CoursePayment> coursePayments; //강좌 결제 리스트
	
	@JsonManagedReference
	@OneToOne(mappedBy = "user")
	private Instructor instructor; //강사
}






