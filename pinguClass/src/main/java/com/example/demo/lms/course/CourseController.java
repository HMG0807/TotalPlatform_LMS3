package com.example.demo.lms.course;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.lms.entity.Course;
import com.example.demo.lms.entity.Lecture;
import com.example.demo.lms.file.FileRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CourseController {

	private final CourseService courseService;	
	private final FileRepository fileRepository; //한민기 추가
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강의 상세 보기 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	
	@GetMapping("/courseDetail/{id}")
	public String course(@PathVariable("id") Integer courseId,Model model) {
		
		Course course = this.courseService.getCourse(courseId);
		
		model.addAttribute("course",course);
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

	
	/******************************************* 한민기 추가 ********************************************/
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강좌 시청 클릭시 > 강의 목록 보기 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/course/lectureList/{id}")
	public String lectureList(@PathVariable("id") Integer courseId, Model model) {
		
		Course course = this.courseService.getCourse(courseId);
		model.addAttribute("course", course);
		
		return "/course/lectureList";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강의 상세 보기 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@GetMapping("/course/lectureDetail")
	public String lectureDetail(@RequestParam(value="lectureId") Integer lectureId, Model model) {
		
		Lecture lecture = this.courseService.getLecture2(lectureId);
		Integer fileId = lecture.getFileId();
		
		//강의 영상 파일이 없다면 > 강의 리스트 페이지로 재이동
		if(this.fileRepository.findById(fileId).isEmpty()) {
			return "redirect:/course/lectureList/" + lecture.getCourse().getCourseId();
		}
		
		LectureDTO lectureDTO = this.courseService.getLectureDTO(lectureId);
		model.addAttribute("lecture", lectureDTO);
		
		return "/lecture/lectureDetail";
	}
	
}

