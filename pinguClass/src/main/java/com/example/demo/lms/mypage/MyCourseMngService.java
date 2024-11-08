package com.example.demo.lms.mypage;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.lms.admin.AdminUserRepository;
import com.example.demo.lms.entity.Category;
import com.example.demo.lms.entity.Course;
import com.example.demo.lms.entity.File;
import com.example.demo.lms.entity.Instructor;
import com.example.demo.lms.entity.Lecture;
import com.example.demo.lms.entity.Qna;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.file.FileService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyCourseMngService {
	
	private final AdminUserRepository adminUserRepository; //User(회원) Repository
	private final InstUseInstructorRepository instUseInstructorRepository; //Instructor(강사) Repository
	private final InstUseCourseRepository instUseCourseRepository; //Course(강좌) Repository
	private final InstUseCategoryRepository instUseCategoryRepository; //Category(카테고리) Repository
	private final InstUseFileRepository instUseFileRepository; //File(파일) Repository
	private final InstUseQnaRepository instUseQnaRepository; //Qna Repository
	private final InstUseLectureRepository instUseLectureRepository; //Lecture(강의) Repository

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 유저 아이디로 강사 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public Instructor getInstructorId(String userId) {
		
		User user = this.adminUserRepository.findById(userId);
		
		return this.instUseInstructorRepository.findByUser(user);
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 특정 강사의 강좌 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<Course> getCourseList(String userId, int startNo, int pageSize) {
		
		Integer instId = getInstructorId(userId).getInstId();
		
		return this.instUseCourseRepository.findCourseByInstId(instId, startNo, pageSize);
	}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 특정 강사의 강좌 조회 : 총 갯수 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public int getCourseCountById(String userId) {
		
		Integer instId = getInstructorId(userId).getInstId();
		
		return this.instUseCourseRepository.countCourseByInstId(instId);
	}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강좌 등록 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public void CourseCreate(String userId, CourseForm courseForm, Integer fileId) {
		
		Course course = new Course();
		course.setInstructor(getInstructorId(userId));
		course.setTitle(courseForm.getTitle());
		course.setContent(courseForm.getContent());
		course.setObjective(courseForm.getObjective());
		course.setPrice(courseForm.getPrice());
		course.setCategory(this.instUseCategoryRepository.findByCategory(courseForm.getCategory())); //카테고리
		course.setFileId(fileId); // 이미지 파일
		course.setDeleteYn("n");
		course.setLastUpdate(LocalDateTime.now());
		
		this.instUseCourseRepository.save(course);
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강좌 수정 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public void CourseModify(Integer courseId, CourseForm courseForm) {
		
		Course course = getCourse(courseId);
		course.setTitle(courseForm.getTitle());
		course.setContent(courseForm.getContent());
		course.setObjective(courseForm.getObjective());
		course.setPrice(courseForm.getPrice());
		course.setCategory(this.instUseCategoryRepository.findByCategory(courseForm.getCategory())); //카테고리
		course.setLastUpdate(LocalDateTime.now());
		
		this.instUseCourseRepository.save(course);
	}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 모든 카테고리 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<Category> getCategoryList() {
		
		return this.instUseCategoryRepository.findAll();
	}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 특정 강좌 1개 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public Course getCourse(Integer courseId) {
		
		return this.instUseCourseRepository.findById(courseId).get();
	}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 파일 ID로 파일객체 반환 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public File getFileName(Integer fileId) {
		return this.instUseFileRepository.findById(fileId).get();
	}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강좌 삭제 > 삭제여부  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public void courseDeleteYn(Integer courseId, String delValue) {
		
		Course course = getCourse(courseId);
		course.setDeleteYn(delValue);
		
		this.instUseCourseRepository.save(course);
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ CourseDTO 조회 : file_id -> file  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public CourseDTO getCourseDTO(Integer courseId) {
		
		Course course = getCourse(courseId);
		
		CourseDTO dto = new CourseDTO();
		dto.setCourseId(course.getCourseId());
		dto.setTitle(course.getTitle());
		dto.setContent(course.getContent());
		dto.setObjective(course.getObjective());
		dto.setPrice(course.getCourseId());
		dto.setLastUpdate(course.getLastUpdate());
		dto.setDeleteYn(course.getDeleteYn());
		dto.setInstructor(course.getInstructor());
		dto.setCategory(course.getCategory());
		dto.setLectures(course.getLectures());
		dto.setRegistrations(course.getRegistrations());
		dto.setReviews(course.getReviews());
		dto.setQnas(course.getQnas());
		dto.setCoursePayments(course.getCoursePayments());
		
		dto.setFile(getFileName(course.getFileId()));
		
		return dto;
	}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강좌 QnA 리스트 조회  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<Qna> getQnaList(Integer courseId, int startNo, int pageSize) {
		return this.instUseQnaRepository.findQnaByCourseId(courseId, startNo, pageSize);
	}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강좌 QnA 리스트 총 갯수 조회  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public int getQnaCountById(Integer courseId) {
		return this.instUseQnaRepository.countQnaByCourseId(courseId);
	}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 특정 강좌의 강의 리스트 조회  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<Lecture> getLectureList(Integer courseId, int startNo, int pageSize) {
		return this.instUseLectureRepository.findLectureByCourseId(courseId, startNo, pageSize);
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 특정 강좌의 강의 리스트 총 갯수 조회  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public int getLectureCountById(Integer courseId) {
		return this.instUseLectureRepository.countLectureByCourseId(courseId);
	}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강의 등록  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public void LectureCreate(Integer courseId, LectureForm lectureForm, Integer fileId) {
		
		Lecture lecture = new Lecture();
		lecture.setFileId(fileId);
		lecture.setTitle(lectureForm.getTitle());
		lecture.setLectureOrder(getLectureCountById(courseId) + 1);
		lecture.setObjective(lectureForm.getObjective());
		lecture.setLastUpdate(LocalDateTime.now());
		lecture.setDeleteYn("n");

		this.instUseLectureRepository.save(lecture);
	}

}








