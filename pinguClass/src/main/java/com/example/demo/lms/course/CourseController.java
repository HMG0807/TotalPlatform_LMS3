package com.example.demo.lms.course;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.lms.entity.Course;
import com.example.demo.lms.entity.Lecture;
import com.example.demo.lms.lecture.LectureService;
import com.example.demo.lms.paging.EzenPaging;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CourseController {

	private final CourseService courseService;	
	private final LectureService lectureService;
	
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강의 상세 보기 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	
	@GetMapping("/courseDetail/{id}")
	public String course(@PathVariable("id") Integer courseId,Model model) {
		
		Course course = this.courseService.getCourse(courseId);
		List<Lecture> lecture = this.lectureService.getLecture(courseId);
		
		model.addAttribute("course",course);
		model.addAttribute("lectureList", lecture);
		return "/category/categoryDetailPage";
	}
	
	
	
	
//	@GetMapping("/courseQna/{id}")
//	public String Qna(@PathVariable("id") Integer courseId, Model model,
//			@RequestParam(value="page", defaultValue="0") int page){
//		
//		EzenPaging ezenPaging = new EzenPaging(page, 3, courseService.getQuestionCountByKeyword(courseId), 5);
//		
//		
//		model.addAttribute("que",);
//		
//		return"/courseQna/courseQnaPage";
//		}


	
}
	
