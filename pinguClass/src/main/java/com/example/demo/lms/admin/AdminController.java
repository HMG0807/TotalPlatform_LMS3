package com.example.demo.lms.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.ecommerce.Entity.Product;
import com.example.demo.lms.entity.Course;
import com.example.demo.lms.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminController {
	
	private final AdminService adService;

	@GetMapping("/admin")
	public String adminLogin() {
		
		return "admin/adminLogin";
	}
	
	/*************************************** 회원 관리 ***************************************/
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 회원 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/admin/userList")
	public String userList(Model model) {
		
		List<User> userList = this.adService.getUserList();
		
		
		model.addAttribute("userList", userList);

		return "admin/adminUserList";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강사 등록 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/admin/setInst/{id}")
	public String createInstructor(Model model, @PathVariable("id") Integer userId) {
		
		this.adService.createInstructor(userId);
		
		return "admin/adminUserList";
	}

	
	/*************************************** 강좌 관리 ***************************************/
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강좌 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/admin/courseList")
	public String courseList(Model model) {
		
		List<Course> courseList = this.adService.getcourseList();
		
		model.addAttribute("courseList", courseList);
		 
		return "admin/adminCourseList";
	}
}











