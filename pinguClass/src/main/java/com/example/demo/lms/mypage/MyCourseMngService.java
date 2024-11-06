package com.example.demo.lms.mypage;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.lms.admin.AdminUserRepository;
import com.example.demo.lms.entity.Category;
import com.example.demo.lms.entity.Course;
import com.example.demo.lms.entity.Instructor;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.file.FileService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyCourseMngService {
	
	private final AdminUserRepository adminUserRepository; //User(회원) Repository
	private final InstUseInstructorRepository instUseInstructorRepository; //Instructor(강사) Repository
	private final InstUseCourseRepository instUseCourseRepository; //Course(강좌) Repository
	private final InstUseCategoryRepository instUseCategoryRepository; //Category(카테고리) Repository

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
		course.setFileId(fileId); // 배너이미지
		course.setDeleteYn("n");
		course.setLastUpdate(LocalDateTime.now());
		
		this.instUseCourseRepository.save(course);
	}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 모든 카테고리 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<Category> getCategoryList() {
		
		return this.instUseCategoryRepository.findAll();
	}

}








