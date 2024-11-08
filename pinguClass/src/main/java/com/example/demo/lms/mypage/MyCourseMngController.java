package com.example.demo.lms.mypage;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.lms.LoginCheck.LoginCheck;
import com.example.demo.lms.entity.Category;
import com.example.demo.lms.entity.Course;
import com.example.demo.lms.file.FileService;
import com.example.demo.lms.paging.EzenPaging;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MyCourseMngController {
	
	private final MyCourseMngService mcmService;
	private final FileService fileService;

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강사 > 등록한 강좌 리스트 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/mypage/instructor/myCourseList")
	public String myCourseList(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		
		String userId = "user3"; //principal.getName() 대체
		
		EzenPaging ezenPaging = new EzenPaging(page, 2, this.mcmService.getCourseCountById(userId), 5);
		List<Course> courseList = this.mcmService.getCourseList(userId, ezenPaging.getStartNo(), ezenPaging.getPageSize());
		
		model.addAttribute("courseList", courseList);
		model.addAttribute("page", ezenPaging);
		
		return "mypage/myCourseMng";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강사 > 강좌 등록 Get ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/mypage/instructor/courseCreate")
	public String myCourseCreateGet(CourseForm courseForm, Model model) {
		
		List<Category> categoryList = this.mcmService.getCategoryList();
		
		model.addAttribute("categoryList", categoryList);
		
		return "mypage/myCourseForm";
	}
	
	// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	// 마이페이지 서브메뉴바 프래그먼트로 빼기
	// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강사 > 강좌 등록 Post ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@PostMapping("/mypage/instructor/courseCreate")
	public String myCourseCreatePost(CourseForm courseForm, HttpServletRequest request, @RequestParam(value = "bannerImg") MultipartFile bannerImg) throws Exception {
		
		String userId = "user3"; //principal.getName() 대체
		
		//파일 복사 + file 테이블 insert, file_id 반환
		Integer fileId = this.fileService.save(request, bannerImg);
		
		this.mcmService.CourseCreate(userId, courseForm, fileId);
		
		return "redirect:/mypage/instructor/myCourseList";
	}
	

}













