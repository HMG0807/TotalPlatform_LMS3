package com.example.demo.lms.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.Entity.Product;
import com.example.demo.ecommerce.repository.ProductRepository;
import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.Course;
import com.example.demo.lms.entity.Instructor;
import com.example.demo.lms.entity.Lecture;
import com.example.demo.lms.entity.Notice;
import com.example.demo.lms.entity.Report;
import com.example.demo.lms.entity.User;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice.This;

@RequiredArgsConstructor
@Service
public class AdminService {
	
	private final AdminUserRepository adminUserRepository; //User(회원) Repository
	private final AdminInstructorRepository adminInstructorRepository; //Instructor(강사) Repository
	private final AdminReportRepository adminReportRepository; //Report(신고) Repository
	private final AdminCourseRepository adminCourseRepository; //Course(강좌) Repository
	private final AdminLectureRepository adminLectureRepository; //Lecture(강의) Repository
	private final AdminCommunityRepository adminCommunityRepository; // Community(커뮤니티) Repository
	private final AdminNoticeRepository adminNoticeRepository; // Notice(공지사항) Repository
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 유저 조회 > 페이징 처리 및 검색 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<User> getUserByKeyword(String kwType, String kw, int startNo, int pageSize){
		
		switch(kwType) {
			case "total": return this.adminUserRepository.findAllByKeyword(kw, startNo, pageSize);
			case "id": return this.adminUserRepository.findAllByUserId(kw, startNo, pageSize);
			case "name": return this.adminUserRepository.findAllByUserName(kw, startNo, pageSize);
		}
		return this.adminUserRepository.findAllByKeyword(kw, startNo, pageSize);
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 유저 조회 > 검색어의 총 갯수 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public int getUserCountByKeyword(String kwType, String kw) {
		
		switch(kwType) {
			case "total": return this.adminUserRepository.countUserByKeyword(kw);
			case "id": return this.adminUserRepository.countUserById(kw);
			case "name": return this.adminUserRepository.countUserByName(kw);
		}
		
		return this.adminUserRepository.countUserByKeyword(kw);
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 모든 회원 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<User> getUserList() {
		return this.adminUserRepository.findAll();
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 회원 1명 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public User getUser(Integer userId) {
		
		Optional<User> user = this.adminUserRepository.findById(userId);
		
		return user.get();
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강사 등록 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public void createInstructor(Integer userId) {
		
		Instructor inst = new Instructor();
		
		this.adminInstructorRepository.save(inst);
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 회원 정지+정지 해제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public void bannedUser(Integer userId, String bannVal) {
		
		User user = getUser(userId);
		user.setBannedYn(bannVal);
		
		this.adminUserRepository.save(user);
	}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 회원 삭제+삭제 해제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public void signoutUser(Integer userId, String singnoutVal) {
		User user = getUser(userId);
		user.setSignoutYn(singnoutVal);
		
		this.adminUserRepository.save(user);
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 특정 회원의 신고 내역 리스트 페이징 처리 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<Report> getUserByReportId(Integer userId, int startNo, int pageSize){
		
		return this.adminReportRepository.findLimitStartIdx(userId, startNo, pageSize);
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 특정 회원의 신고당한 횟수 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public int getUserCountByReportId(Integer userId) {
		
		
		return this.adminReportRepository.countReport(userId);
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 모든 강좌 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<Course> getcourseList() {
		return this.adminCourseRepository.findAll();
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강좌 1개 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public Course getCourse(Integer courseId) {
		
		Optional<Course> course = this.adminCourseRepository.findById(courseId);
		
		return course.get();
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강좌 조회 > 페이징 처리 및 검색 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<Course> getCourseByKeyword(String kwType, String kw, int startNo, int pageSize) {
		switch(kwType) {
			case "total": return this.adminCourseRepository.findAllByKeyword(kw, startNo, pageSize);
			case "course": return this.adminCourseRepository.findAllByCourseName(kw, startNo, pageSize);
			case "lecture": return this.adminCourseRepository.findAllByLectureName(kw, startNo, pageSize);
		}
		return this.adminCourseRepository.findAllByKeyword(kw, startNo, pageSize);
	}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강좌 조회 > 검색어의 총 갯수 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public int getCourseCountByKeyword(String kwType, String kw) {
		switch(kwType) {
			case "total": return this.adminCourseRepository.countUserByKeyword(kw);
			case "course": return this.adminCourseRepository.countCourseByName(kw);
			case "lecture": return this.adminCourseRepository.countLectureByName(kw);
		}
		
		return this.adminCourseRepository.countUserByKeyword(kw);
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강좌 삭제+삭제 해제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public void deleteCourse(Integer courseId, String deleteVal) {
		Course course = getCourse(courseId);
		course.setDeleteYn(deleteVal);
		
		this.adminCourseRepository.save(course);
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 특정 강좌의 강의 내역 리스트 페이징 처리 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<Lecture> getLectureByCourseId(Integer courseId, int startNo, int pageSize){
		
		return this.adminLectureRepository.findLectureLimitStartIdx(courseId, startNo, pageSize);
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 특정 강좌의 강의 내역 갯수 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public int getLectureCountByCourseId(Integer courseId) {
		
		
		return this.adminLectureRepository.countLecture(courseId);
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강의 삭제+삭제 해제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public void deleteLecture(Integer lectureId, String deleteVal) {
		Lecture lecture = this.adminLectureRepository.findById(lectureId).get();
		lecture.setDeleteYn(deleteVal);
		
		this.adminLectureRepository.save(lecture);
	}

    /* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 커뮤니티 글 조회 > 검색어의 총 갯수 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public int getCommunityCountByKeyword(String kwType, String kw) {
		/*
		switch(kwType) {
			case "total": return this.adminCommunuityRepository.countUserByKeyword(kw);
			case "title": return this.adminCommunuityRepository.countUserById(kw);
			case "name": return this.adminCommunuityRepository.countUserByName(kw);
		}
		*/
		
		return this.adminCommunityRepository.countCommunityByKeyword(kw);
	}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 커뮤니티 전체글 보기 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	
	public List<Community> getCommunityByKeyword(String kw, int startNo, int pageSize) {
		return this.adminCommunityRepository.findCommunityLimitStartIdx(kw, startNo, pageSize);
	}
	
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 커뮤니티 작성글 보기 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */	

	public Community getCommunity(Integer cmId) {
		
		Optional<Community> community = this.adminCommunityRepository.findById(cmId);
		
		return community.get();
	}	
	

	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 커뮤니티 검색 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */	

	public List<Community> getCommunityContentByKeyword(String kwType, String kw, int startNo, int pageSize){
	
	switch(kwType) {
	case "total": return this.adminCommunityRepository.findAllByKeyword(kw, startNo, pageSize);
	case "title": return this.adminCommunityRepository.findAllByCommunityTitle(kw, startNo, pageSize);
	case "name": return this.adminCommunityRepository.findAllByUserName(kw, startNo, pageSize);}

return this.adminCommunityRepository.findAllByKeyword(kw, startNo, pageSize);

	}
	

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 작성글 삭제+삭제 해제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public void signoutContent(Integer cmId, String singnoutVal) {
		Community community = getCommunity(cmId);
		community.setDeleteYn(singnoutVal);
		
		this.adminCommunityRepository.save(community);
	}

	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 공지사항 전체 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<Notice> getNoticeByKeyword(String kw, int startNo, int pageSize) {
		return this.adminNoticeRepository.findNoticeLimitStartIdx(kw, startNo, pageSize);}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 커뮤니티 작성글 보기 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */	

	public Notice getNotice(Integer noticeId) {
			
		Optional<Notice> notice = this.adminNoticeRepository.findById(noticeId);
			
		return notice.get();
		}	
				
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 공지사항 작성글 삭제+삭제 해제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public void signoutDetail(Integer noticeId, String singnoutVal) {
		Notice notice = getNotice(noticeId);
		notice.setDeleteYn(singnoutVal);
		
		this.adminNoticeRepository.save(notice);
	}	
	
		
	}	
	

	
	
	
	
	
	

	
	
	
	
	
	
	
	
	














