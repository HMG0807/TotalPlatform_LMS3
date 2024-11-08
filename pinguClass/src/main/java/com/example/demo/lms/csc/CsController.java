package com.example.demo.lms.csc;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.lms.admin.AdminNoticeService;
import com.example.demo.lms.admin.AdminService;
import com.example.demo.lms.community.CommunityForm;
import com.example.demo.lms.entity.CsQuestion;
import com.example.demo.lms.entity.Notice;
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
	private final AdminService adminService;
	private final UserService userService;
	
	
	// 1:1문의 목록 + 페이징 _ 이순
	//@PreAuthorize("isAuthenticated()")
	@GetMapping("/list")
	public String csCenter(Model model,
			@RequestParam(value="page", defaultValue="0") int page, 
			Principal principal) {
			//@RequestParam(value="chk", defaultValue="on") String chk) {
		
		// [페이징] 현재 페이지에 보일 개수, 언더바 사이즈
	    EzenPaging ezenPaging = new EzenPaging(page, 10, this.adminService.getCsQuestionCount(), 5);
	    
	    // [페이징] 페이지 이동시 문의글 변경
	    List<CsQuestion> csList = this.adminService.getCsQuestionByLimit(ezenPaging.getStartNo(), ezenPaging.getPageSize()); 
	    
	    model.addAttribute("csList", csList);
	    model.addAttribute("paging", ezenPaging);
	    
		return "csc/csPage";
	}
	
	// 문의글 상세
	//@PreAuthorize("isAuthenticated()")
	@GetMapping("/detail/{id}")
	public String csDetail(Model model, @PathVariable("id") Integer id, 
			Principal principa)throws UserException {
			CsQuestion csDetail = this.csQuestionService.getDetail(id);
			model.addAttribute("csDetail", csDetail);
		
		return "csc/csDetail";
		
	}
	
	
	// 문의글 수정
	//@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String CsQuestionModify(CsQuestionForm csQuestionForm, @PathVariable("id") Integer id,
			Principal principal, Model model) {
		
		try {
			CsQuestion csQuestionModify = this.csQuestionService.getDetail(id);
			model.addAttribute("csQuestionModify", csQuestionModify);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				return "csc/csQuestionModify";
	}
	
	//@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String CsQuestionModify(Model model, @PathVariable("id") Integer id,
			@RequestParam(value="content") String content,
			@RequestParam(value="title") String title,
			@Valid CsQuestionForm csQuestionForm, BindingResult bindingResult,
			Principal principal) throws UserException{
				
		CsQuestion csQuestion = this.csQuestionService.getDetail(id);
		
		if(bindingResult.hasErrors()) {
			return "csc/csQuestionModify";
		}
		
		try {
			User user = this.userService.getUser(id);
			this.csQuestionService.modify(csQuestion, csQuestionForm.getTitle(), csQuestionForm.getContents());
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return String.format("redirect:/css/detail/%s", id);
		
	}
	
	
	// 문의글 삭제
	@GetMapping("/delete/{id}")
	public String csQuestionDelete(@PathVariable("id") Integer id) throws UserException {
		CsQuestion csQuestionDelete = this.csQuestionService.getDetail(id);
		this.csQuestionService.delete(csQuestionDelete);
		
		return "redirect:/csc/list";
		
	}

	
	
	

}
