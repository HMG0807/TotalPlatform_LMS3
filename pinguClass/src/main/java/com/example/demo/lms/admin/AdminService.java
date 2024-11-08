package com.example.demo.lms.admin;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.lms.entity.Admin;
import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.Course;
import com.example.demo.lms.entity.CsAnswer;
import com.example.demo.lms.entity.CsQuestion;
import com.example.demo.lms.entity.Instructor;
import com.example.demo.lms.entity.Lecture;
import com.example.demo.lms.entity.Notice;
import com.example.demo.lms.entity.NoticeFile;
import com.example.demo.lms.entity.Report;
import com.example.demo.lms.entity.User;

import lombok.RequiredArgsConstructor;


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
	private final AdminRepository adminRepository; //Admin(관리자) Repository
	private final AdminCsQuestionRepository adminCsQuestionRepository;
	private final AdminCsAnswerRepository adminCsAnswerRepository;
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
	public void createInstructor(Integer userId, Integer fileId) {
		
		Instructor inst = new Instructor();
		inst.setUser(getUser(userId));
		inst.setFileId(fileId);
		
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
		
		switch(kwType) {
			case "total": return this.adminCommunityRepository.countCommunityByKeyword(kw);
			case "title": return this.adminCommunityRepository.countCommunityByTitle(kw);
			case "name": return this.adminCommunityRepository.countCommunityByName(kw);
		}
		
		
		return this.adminCommunityRepository.countCommunityByKeyword(kw);
	}
		

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 커뮤니티 전체글 보기 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	
	public List<Community> getCommunityByKeyword(String kwType,String kw, int startNo, int pageSize) {
		switch(kwType) {
		case "total": return this.adminCommunityRepository.findAllByKeyword(kw, startNo, pageSize);
		case "title": return this.adminCommunityRepository.findAllByCommunityTitle(kw, startNo, pageSize);
		case "name": return this.adminCommunityRepository.findAllByUserName(kw, startNo, pageSize);
	}
		
		
		return this.adminCommunityRepository.findAllByKeyword(kw, startNo, pageSize);
	}
	
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 커뮤니티 작성글 보기 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */	

	public Community getCommunity(Integer cmId) {
		
		Optional<Community> community = this.adminCommunityRepository.findById(cmId);
		
		return community.get();
	}	
	



	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 작성글 삭제+삭제 해제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public void signoutContent(Integer cmId, String singnoutVal) {
		Community community = getCommunity(cmId);
		community.setDeleteYn(singnoutVal);
		
		this.adminCommunityRepository.save(community);
	}

	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 공지사항 전체 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<Notice> getNoticeByKeyword(int startNo, int pageSize) {
		return this.adminNoticeRepository.findNoticeLimitStartIdx(startNo, pageSize);}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 공지사항 페이징 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	
	public int getNoticeCountByKeyword() {

		return this.adminNoticeRepository.countNoticeByKeyword();
	}	
	

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
	
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 공지사항 등록 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public void register(String title, String content, String adminCode){
		
		Admin admin = this.adminRepository.findByAdminCode(adminCode);
		
		Notice n = new Notice();
		n.setTitle(title);
		n.setContent(content);
		n.setLastUpdate(LocalDateTime.now());
		n.setAdmin(admin);
		n.setDeleteYn("n");

		
		this.adminNoticeRepository.save(n);
	}

	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 공지사항 수정 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public void modify(Notice n, String title, String content) {
		n.setTitle(title);
		n.setContent(content);
		this.adminNoticeRepository.save(n);
	}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 1대1 문의 전체 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
//	public List<CsQuestion> getCsQuetionByKeyword(int startNo, int pageSize) {
//
//		return this.adminCsQuestionRepository.findCsQuestionLimitStartIdx(startNo, pageSize);}
	
	public List<CsQuestion> getCsQuetionByKeyword(String kwType,String kw, int startNo, int pageSize) {
		switch(kwType) {
		case "total": return this.adminCsQuestionRepository.findAllByKeyword(kw, startNo, pageSize);
		case "title": return this.adminCsQuestionRepository.findAllByQuestionTitle(kw, startNo, pageSize);
		case "name": return this.adminCsQuestionRepository.findAllByUserName(kw, startNo, pageSize);
	}
		
		
		return this.adminCsQuestionRepository.findAllByKeyword(kw, startNo, pageSize);
	}	

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 1대1 문의글 조회 > 검색어의 총 갯수  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */

	public int getCsQuestionCountByKeyword(String kwType, String kw) {
		
		switch(kwType) {
			case "total": return this.adminCsQuestionRepository.countQuestionByKeyword(kw);
			case "title": return this.adminCsQuestionRepository.countQuestionByTitle(kw);
			case "name": return this.adminCsQuestionRepository.countQuestionByName(kw);
		}
		
		
		return this.adminCommunityRepository.countCommunityByKeyword(kw);
	}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 1대1 질문글 보기  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	
	public CsQuestion getQuestion(Integer questionId) {

		Optional<CsQuestion> question = this.adminCsQuestionRepository.findById(questionId);
		
		return question.get();				

    }
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 답변 등록 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public void registAnswer(Integer questionId, String title, String content, String adminCode){
		
		Admin admin = this.adminRepository.findByAdminCode(adminCode);
		CsQuestion question = this.adminCsQuestionRepository.findById(questionId).get();
		
		CsAnswer c = new CsAnswer();
		c.setCsQuestion(question);
		c.setAdmin(admin);
		c.setTitle(title);
		c.setContent(content);
		c.setLastUpdate(LocalDateTime.now());
		c.setDeleteYn("n");
		
		this.adminCsAnswerRepository.save(c);
	}

	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 답변 수정 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public void modifyAnswer(Integer answerId, String title, String content, String adminCode) {
		
		//수정할 답변 id로 답변 객체 조회
		CsAnswer c = this.adminCsAnswerRepository.findById(answerId).get();
		
		Admin admin = this.adminRepository.findByAdminCode(adminCode);
		
		//답변 글 수정
		c.setTitle(title);
		c.setContent(content);
		c.setAdmin(admin);
		c.setLastUpdate(LocalDateTime.now());
		
		//답변 글 db 적용
		this.adminCsAnswerRepository.save(c);
	}


	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 답변 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */

	public CsAnswer getAnswer(Integer answerId){
		Optional<CsAnswer> a = this.adminCsAnswerRepository.findById(answerId);

			return a.get();
	}


	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 1대1 문의 답변 삭제+삭제 해제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public void signoutAnswer(Integer answerId, String singnoutVal) {
		CsAnswer answer = getAnswer(answerId);
		answer.setDeleteYn(singnoutVal);
		
		this.adminCsAnswerRepository.save(answer);
	}

	public int getQuestionCountByAll(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<CsQuestion> getUserByKeyword(int i, int startNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}


	


	

}






