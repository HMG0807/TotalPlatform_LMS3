package com.example.demo.lms.mypage;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.lms.Authuser.Authuser;
import com.example.demo.lms.LoginCheck.LoginCheck;
import com.example.demo.lms.entity.Category;
import com.example.demo.lms.entity.Course;
import com.example.demo.lms.entity.Lecture;
import com.example.demo.lms.entity.Qna;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.file.FileService;
import com.example.demo.lms.paging.EzenPaging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MyCourseMngController {
	
	private final MyCourseMngService mcmService;
	private final FileService fileService;

	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강사 > 등록한 강좌 리스트 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@LoginCheck
	@GetMapping("/mypage/instructor/myCourseList")
	public String myCourseList(Model model, @RequestParam(value="page", defaultValue="0") int page,
								@Authuser User user) {
		
		String userId = user.getId(); //principal.getName() 대체 > "hmg234"
		
		//유저가 강사가 아닐 경우
		if(user.getInstructor() == null) {
			model.addAttribute("instructor", user.getInstructor()); //강사인지 아닌지 확인
			return "mypage/myCourseMng";
		}
		
		EzenPaging ezenPaging = new EzenPaging(page, 5, this.mcmService.getCourseCountById(userId), 5);
		List<Course> courseList = this.mcmService.getCourseList(userId, ezenPaging.getStartNo(), ezenPaging.getPageSize());
		
		model.addAttribute("courseList", courseList);
		model.addAttribute("page", ezenPaging);
		model.addAttribute("instructor", user.getInstructor());
		
		return "mypage/myCourseMng";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강사 > 강좌 등록 Get ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@LoginCheck
	@GetMapping("/mypage/instructor/courseCreate")
	public String myCourseCreateGet(CourseForm courseForm, Model model) {
		
		List<Category> categoryList = this.mcmService.getCategoryList();
		
		model.addAttribute("categoryList", categoryList);
		
		return "mypage/myCourseForm";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강사 > 강좌 등록 Post ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@LoginCheck
	@PostMapping("/mypage/instructor/courseCreate")
	public String myCourseCreatePost(@Valid CourseForm courseForm, BindingResult bindingResult, 
									HttpServletRequest request, Model model, @Authuser User user) throws Exception {
		
		if(bindingResult.hasErrors()) {
			//@Valid 유효성 검사 실패시 myCourseForm.html로 이동
			List<Category> categoryList = this.mcmService.getCategoryList();
			
			model.addAttribute("categoryList", categoryList);
			
			return "mypage/myCourseForm";
		}
		
		//파일 복사 + file 테이블 insert, file_id 반환
		Integer fileId = this.fileService.save(request, courseForm.getMainImg()); //메인 이미지
		
		String userId = user.getId(); //principal.getName() 대체
		this.mcmService.CourseCreate(userId, courseForm, fileId);
		
		return "redirect:/mypage/instructor/myCourseList";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강사 > 강좌 수정 Get ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@LoginCheck
	@GetMapping("/mypage/instructor/courseModify/{id}")
	public String myCourseModifyGet(@PathVariable("id") Integer courseId, CourseForm courseForm, Model model) {
		
		List<Category> categoryList = this.mcmService.getCategoryList();
		Course course = this.mcmService.getCourse(courseId);
		
		courseForm.setCategory(course.getCategory().getCategory());
		courseForm.setContent(course.getContent());
		courseForm.setObjective(course.getObjective());
		courseForm.setPrice(course.getPrice());
		courseForm.setTitle(course.getTitle());
		
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("courseDTO", this.mcmService.getCourseDTO(courseId));
		
		return "mypage/myCourseForm";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강사 > 강좌 수정 Post ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@LoginCheck
	@PostMapping("/mypage/instructor/courseModify/{id}")
	public String myCourseModifyPost(@Valid CourseForm courseForm, BindingResult bindingResult, Model model,
									@PathVariable("id") Integer courseId) throws Exception {
		
		if(bindingResult.getErrorCount() > 1) {
			//@Valid 유효성 검사 실패시 myCourseForm.html로 이동
			List<Category> categoryList = this.mcmService.getCategoryList();
			Course course = this.mcmService.getCourse(courseId);
			
			courseForm.setCategory(course.getCategory().getCategory());
			courseForm.setContent(course.getContent());
			courseForm.setObjective(course.getObjective());
			courseForm.setPrice(course.getPrice());
			courseForm.setTitle(course.getTitle());
			
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("courseDTO", this.mcmService.getCourseDTO(courseId));
			
			return "mypage/myCourseForm";
		}
		
		this.mcmService.CourseModify(courseId, courseForm);
		
		return "redirect:/mypage/instructor/myCourseList";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강사 > 강좌 삭제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@LoginCheck
	@GetMapping("/mypage/instructor/courseDelete/{id}")
	public String myCourseDelete(@PathVariable("id") Integer courseId) {
		
		// 강좌 삭제 > 강좌, 배너이미지 삭제여부 y 변경
		this.mcmService.courseDeleteYn(courseId, "y");
		
		return "redirect:/mypage/instructor/myCourseList";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강사 > 강좌 QnA 내역 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@LoginCheck
	@GetMapping("/mypage/instructor/courseQnaSelect/{id}")
	public String myCourseQnaSelect(@PathVariable("id") Integer courseId, Model model,
									@RequestParam(value="page", defaultValue="0") int page) {
		
		EzenPaging ezenPaging = new EzenPaging(page, 5, this.mcmService.getQnaCountById(courseId), 5);
		List<Qna> qnaList = this.mcmService.getQnaList(courseId, ezenPaging.getStartNo(), ezenPaging.getPageSize());
		
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("page", ezenPaging);
		
		return "mypage/myCourseQnaList";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강사 > 특정 강좌의 강의 내역 조회 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@LoginCheck
	@GetMapping("/mypage/instructor/lectureMng/{id}")
	public String myCourselectureMng(@PathVariable("id") Integer courseId, Model model) {
		
		List<Lecture> lectureList = this.mcmService.getLectureList(courseId);
		
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("courseId", courseId);
		
		return "mypage/myCourseLectureMng";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강사 > 특정 강좌의 강의 내역 순서 변경 Get ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@LoginCheck
	@GetMapping("/mypage/instructor/lectureMng/orderChange/{id}")
	public String myCourselectureOrderChange(@PathVariable("id") Integer courseId, Model model) {
		
		List<Lecture> lectureList = this.mcmService.getLectureList(courseId);
		
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("courseId", courseId);
		
		return "mypage/myCourseLectureOrderChange";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강사 > 특정 강좌의 강의 내역 순서 변경 Post ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@LoginCheck
	@PostMapping("/mypage/instructor/lectureMng/orderChange/{id}")
	public String myCourselectureOrderChangePost(@PathVariable("id") Integer courseId,
												@RequestParam("orderValue") List<Integer> orderValue,
												@RequestParam("lectureId") List<Integer> lectureIdList) {
		
		this.mcmService.lectureOrderChange(lectureIdList, orderValue);
		
		return "redirect:/mypage/instructor/lectureMng/" + courseId;
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강사 > 강의 등록 Get ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@LoginCheck
	@GetMapping("/mypage/instructor/lectureCreate/{id}")
	public String myCourseLectureCreateGet(LectureForm lectureForm) {
		
		return "mypage/myCourseLectureForm";
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강사 > 강의 등록 Post ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	@LoginCheck
	@PostMapping("/mypage/instructor/lectureCreate/{id}")
	public String myCourseLectureCreatePost(@PathVariable("id") Integer courseId,
											@Valid LectureForm lectureForm, BindingResult bindingResult, 
											HttpServletRequest request, Model model) throws Exception {
		
		if(bindingResult.hasErrors()) {
			//@Valid 유효성 검사 실패시 myCourseForm.html로 이동
			
			return "mypage/myCourseLectureForm";
		}
		
		//파일 복사(강의 영상) + file 테이블 insert, file_id 반환
		Integer fileId = this.fileService.save(request, lectureForm.getLectureVideo());
		
		this.mcmService.LectureCreate(courseId, lectureForm, fileId);
		
		return "redirect:/mypage/instructor/lectureMng/" + courseId;
	}
	
	
	
}













