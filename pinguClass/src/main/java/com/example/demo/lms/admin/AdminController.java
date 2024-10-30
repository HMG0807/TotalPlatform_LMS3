package com.example.demo.lms.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.ecommerce.Entity.Product;
import com.example.demo.lms.entity.Course;
import com.example.demo.lms.entity.Lecture;
import com.example.demo.lms.entity.Report;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.paging.EzenPaging;

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
	public String userList(Model model, @RequestParam(value="page", defaultValue="0") int page, 
							@RequestParam(value = "kw", defaultValue = "") String kw,
							@RequestParam(value = "kwType", defaultValue = "") String kwType) {
		
		//EzenPaging ezenPaging = new EzenPaging(현재 페이지 번호, 페이지당 글 갯수, 총 글 갯수, 페이징 버튼 갯수)
		EzenPaging ezenPaging = new EzenPaging(page, 10, adService.getUserCountByKeyword(kwType, kw), 5);
		List<User> userList = this.adService.getUserByKeyword(kwType, kw, ezenPaging.getStartNo(), ezenPaging.getPageSize());
		
		model.addAttribute("userList", userList);
		model.addAttribute("page", ezenPaging);
		model.addAttribute("kw", kw);
		model.addAttribute("kwType", kwType);

		return "admin/adminUserList";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강사 등록 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/admin/setInst/{id}")
	public String createInstructor(@PathVariable("id") Integer userId) {
		
		this.adService.createInstructor(userId);
		
		return "admin/adminUserList";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 회원 정지 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/admin/banned/{id}")
	public String bannedUser(@PathVariable("id") Integer userId) {
		
		this.adService.bannedUser(userId, "y");
		
		return "redirect:/admin/userList";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 회원 정지 해제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/admin/banncancel/{id}")
	public String bannCancelUser(@PathVariable("id") Integer userId) {
		
		this.adService.bannedUser(userId, "n");
		
		return "redirect:/admin/userList";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 회원 삭제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/admin/signout/{id}")
	public String signoutUser(@PathVariable("id") Integer userId) {
		
		this.adService.signoutUser(userId, "y");
		
		return "redirect:/admin/userList";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 회원 삭제 해제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/admin/signoutcancel/{id}")
	public String signoutCancelUser(@PathVariable("id") Integer userId) {
		
		this.adService.signoutUser(userId, "n");
		
		return "redirect:/admin/userList";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 회원 신고 내역 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/admin/reportList/{id}")
	public String reportUserList(@PathVariable("id") Integer userId, Model model,
								 @RequestParam(value="page", defaultValue="0") int page) {
		
		//EzenPaging ezenPaging = new EzenPaging(현재 페이지 번호, 페이지당 글 갯수, 총 글 갯수, 페이징 버튼 갯수)
		EzenPaging ezenPaging = new EzenPaging(page, 10, adService.getUserCountByReportId(userId), 5);
		List<Report> reportList = this.adService.getUserByReportId(userId, ezenPaging.getStartNo(), ezenPaging.getPageSize());
		
		model.addAttribute("reportList", reportList);
		model.addAttribute("page", ezenPaging);
		
		return "/admin/adminUserListReport";
	}
	
	
	/*************************************** 강좌 관리 ***************************************/
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강좌 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/admin/courseList")
	public String courseList(Model model, @RequestParam(value="page", defaultValue="0") int page, 
							@RequestParam(value = "kw", defaultValue = "") String kw,
							@RequestParam(value = "kwType", defaultValue = "") String kwType) {
		
		EzenPaging ezenPaging = new EzenPaging(page, 10, adService.getCourseCountByKeyword(kwType, kw), 5);
		List<Course> courseList = this.adService.getCourseByKeyword(kwType, kw, ezenPaging.getStartNo(), ezenPaging.getPageSize());
		
		model.addAttribute("courseList", courseList);
		model.addAttribute("page", ezenPaging);
		model.addAttribute("kw", kw);
		model.addAttribute("kwType", kwType);
		
		return "admin/adminCourseList";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강좌 삭제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/admin/deletecourse/{id}")
	public String deleteCourse(@PathVariable("id") Integer courseId) {
		
		this.adService.deleteCourse(courseId, "y");
		
		return "redirect:/admin/courseList";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강좌 삭제 해제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/admin/deletecourse/cancel/{id}")
	public String deleteCancelCourse(@PathVariable("id") Integer courseId) {
		
		this.adService.deleteCourse(courseId, "n");
		
		return "redirect:/admin/courseList";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 특정 강좌의 강의 내역 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/admin/lectureList/{id}/{page}")
	public String lectureList(@PathVariable("id") Integer courseId, Model model,
								@PathVariable("page") int page) {
		
		//EzenPaging ezenPaging = new EzenPaging(현재 페이지 번호, 페이지당 글 갯수, 총 글 갯수, 페이징 버튼 갯수)
		EzenPaging ezenPaging = new EzenPaging(page, 10, adService.getLectureCountByCourseId(courseId), 5);
		List<Lecture> lectureList = this.adService.getLectureByCourseId(courseId, ezenPaging.getStartNo(), ezenPaging.getPageSize());
		
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("page", ezenPaging);
		
		return "/admin/adminCourseLectureList";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강의 삭제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/admin/deletelecture/{lectureId}/{courseId}/{page}")
	public String deleteLecture(@PathVariable("lectureId") Integer lectureId,
								@PathVariable("courseId") Integer courseId,
								@PathVariable("page") Integer page) {
		
		this.adService.deleteLecture(lectureId, "y");
		
		return "redirect:/admin/lectureList/" + courseId + "/" + page;
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강의 삭제 해제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/admin/deletelecture/cancel/{lectureId}/{courseId}/{page}")
	public String deleteCancelLecture(@PathVariable("lectureId") Integer lectureId,
										@PathVariable("courseId") Integer courseId,
										@PathVariable("page") Integer page) {
		
		this.adService.deleteLecture(lectureId, "n");
		
		return "redirect:/admin/lectureList/" + courseId + "/" + page;
	}
	
}











