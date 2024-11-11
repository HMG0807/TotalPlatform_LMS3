package com.example.demo.lms.category;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.lms.LoginCheck.LoginCheck;
import com.example.demo.lms.course.CourseDTO;
import com.example.demo.lms.course.CourseService;
import com.example.demo.lms.entity.Course;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CategoryController {

	private final CategoryService categoryService; 
	/*************************************** 카테고리 ***************************************/
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 카테고리 창으로 보내기 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/category")
	public String category(@RequestParam(name="keyword",required = false) String kw,
						   @RequestParam(name="category",required = false) Integer Id,
						  // @RequestParam(name="categorySelect", required = false) String select,
						   Model model) {
		
		List<CourseDTO> courseList = null;
		
		courseList  = this.categoryService.getAllCourseDTOList();
			
		if(Id!=null) {
			courseList = this.categoryService.getCourseByCategoryId(Id);
		}
		
		if(kw!=null) {
			courseList = this.categoryService.getCourseByKeyWord(kw);
		}
		
//		if(select != null) {
//			if(select.equals("시간")) {
//				courseList = this.categoryService.getAllbyTime();
//			}
//			
//			if(select.equals("인기")) {
//				courseList = this.categoryService.getByPopularity();
//			}
//		}
		
		
		
		model.addAttribute("courseList", courseList);
		
		return "category/categorypage";
		
		
	}
		
	
	
	
	
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 카테고리 창으로 보내기 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */	
//	@GetMapping("/category/{id}")
//	public String category( Model model,@PathVariable("id") Integer categoryId) {
//
//		//List<Course> courseList = this.categoryService.getCourseByKeyword();
//		
//		List<CourseDTO> courseList = this.categoryService.getCourseDTOList(categoryId);
//				
//		model.addAttribute("courseList", courseList);
//
//		return "/category/categorypage";
//	}

	
	
	
	
	
}
