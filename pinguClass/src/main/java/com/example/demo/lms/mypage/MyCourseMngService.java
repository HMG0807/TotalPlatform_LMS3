package com.example.demo.lms.mypage;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.lms.admin.AdminUserRepository;
import com.example.demo.lms.entity.Course;
import com.example.demo.lms.entity.Instructor;
import com.example.demo.lms.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyCourseMngService {
	
	private final AdminUserRepository adminUserRepository; //User(회원) Repository
	private final InstUseInstructorRepository instUseInstructorRepository; //Instructor(강사) Repository
	private final InstUseCourseReRepository instUseCourseReRepository; //Course(강좌) Repository

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 유저 아이디로 강사 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public Instructor getInstructorId(String userId) {
		
		User user = this.adminUserRepository.findById(userId);
		
		return this.instUseInstructorRepository.findByUser(user);
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 특정 강사의 강좌 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public List<Course> getCourseList(String userId, int startNo, int pageSize) {
		
		Integer instId = getInstructorId(userId).getInstId();
		
		return this.instUseCourseReRepository.findCourseByInstId(instId, startNo, pageSize);
	}

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 특정 강사의 강좌 조회 : 총 갯수 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public int getCourseCountById(String userId) {
		
		Integer instId = getInstructorId(userId).getInstId();
		
		return this.instUseCourseReRepository.countCourseByInstId(instId);
	}

}
