package com.example.demo.lms.csc;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.lms.Authuser.Authuser;
import com.example.demo.lms.LoginCheck.LoginCheck;
import com.example.demo.lms.entity.CsQuestion;

import com.example.demo.lms.entity.User;
import com.example.demo.lms.paging.EzenPaging;
import com.example.demo.lms.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/csc")
@RequiredArgsConstructor
@Controller
public class CsController {
	
	private final CsQuestionService csQuestionService;
	private final UserService userService;
	
	// 1:1문의 목록
	@LoginCheck
	@GetMapping("/list")
	public String CsCenter(Model model, @Authuser User user,
			@RequestParam(value="page", defaultValue="0") int page) {
		
		
		// 1:1 문의 페이징
	    EzenPaging paging = new EzenPaging(page, 10, this.csQuestionService.getQuestionCountByAll(user.getUserId()), 5); 
	    List<CsQuestion> csQuestion = this.csQuestionService.getQuestionByLimit(user.getUserId(), paging.getStartNo(), paging.getPageSize()); // 나중에 14->id로 바꾸기
		
	    model.addAttribute("paging", paging);
	    model.addAttribute("csQuestion", csQuestion);
	    
		return "csc/csQuestionList";
	}
	
	
	
	
	// 문의글 상세
	@LoginCheck
	@GetMapping("/detail/{id}")
	public String CsDetail(Model model, @PathVariable("id") Integer id, @Authuser User user) throws UserException {
		CsQuestion csDetail = this.csQuestionService.getDetail(id);
		model.addAttribute("csDetail", csDetail);
		
		return "csc/csQuestionDetail";
	}
	
	
	
	// 문의글 생성
	@LoginCheck
	@GetMapping("/create")
	public String CsQuestionCreate(CsForm csForm) {
			
		return "csc/csQuestionForm";
			
	}
		
	@LoginCheck
	@PostMapping("/create")
	public String CsQuestionCreate(@Valid CsForm csForm, BindingResult bindingResult, 
			@Authuser User user) throws UserException {
		
		
		System.out.println("책임을 물것이야!!!!!!!");
		// 제목 내용 안쓰면 폼에서 못 나간다!
		if(bindingResult.hasErrors()) {
			return "csc/csQuestionForm";
		}
		System.out.println("ang");
		
		// 제대로 썼을 때 저장
		try {
			// 유저 이름 불러오기
			System.out.println("책임을 물것이야!!!!!!!22222222");
			User userId;
			userId = userService.getUser(user.getUserId());
			// 유저 정보에 입력한 제목, 내용, 유저명 저장
			this.csQuestionService.create(csForm.getTitle(), csForm.getContent(), userId);
		} 
		
		// 정보 불러오기 오류
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
			
		// 성공하면 목록으로 돌아가
		return "redirect:/csc/list";
	}
		
		
		
	// 문의글 수정
	@LoginCheck
	@GetMapping("/modify/{id}")
	public String CsModify(CsForm csForm, @PathVariable("id") Integer id,
		@Authuser User user, Model model) throws UserException {
			
		try {
			CsQuestion csModify = this.csQuestionService.getDetail(id);
			model.addAttribute("csModify", csModify);
		} catch (Exception e) {

			e.printStackTrace();
		}

			
		return "csc/csQuestionModify";
	}
		
	@LoginCheck
	@PostMapping("/modify/{id}")
	public String CsModify(Model model, @PathVariable("id") Integer id,
			@RequestParam(value="content") String content,
			@RequestParam(value="title") String title,
			@Valid CsForm csForm, BindingResult bindingResult,
			@Authuser User user) throws UserException {
					
		CsQuestion csQuestion = this.csQuestionService.getDetail(id);
					
					
					
		if(bindingResult.hasErrors()) {
			return "csc/csQuestionModify";
		}
					
		try {
			this.csQuestionService.modify(csQuestion, csForm.getTitle(),
					csForm.getContent());
							
		} catch (Exception e) {

			e.printStackTrace();
						
		}
					
					
		return String.format("redirect:/csc/detail/%s", id);
	}
		
		
		
		
		// 문의글 삭제
		@LoginCheck
		@GetMapping("/delete/{id}")
		public String CsDelete(@PathVariable("id") Integer id) throws Exception {
			CsQuestion csDelete = this.csQuestionService.getDetail(id);
			this.csQuestionService.delete(csDelete);
			
			return "redirect:/csc/list";
			
		}
		
		
		
	
	
	
	

}
