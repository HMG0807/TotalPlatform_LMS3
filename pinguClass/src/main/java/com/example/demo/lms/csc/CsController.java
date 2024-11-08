package com.example.demo.lms.csc;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.lms.admin.AdminNoticeService;
import com.example.demo.lms.admin.AdminService;
import com.example.demo.lms.entity.CsQuestion;
import com.example.demo.lms.entity.Notice;
import com.example.demo.lms.paging.EzenPaging;

import lombok.RequiredArgsConstructor;

@RequestMapping("/csc")
@RequiredArgsConstructor
@Controller
public class CsController {
	
	private final CsQuestionService qr;
	private final AdminService ar;
	private final AdminNoticeService ans;
	
	
	// 1:1문의 목록
	//@PreAuthorize("isAuthenticated()")
	@GetMapping("")
	public String csCenter(Model model,
			@RequestParam(value="page1", defaultValue="0") int page1, 
			@RequestParam(value="page2", defaultValue="0") int page2,
			@RequestParam(value="pageType", defaultValue="inquiry") String pageType,
			Principal principal,
			@RequestParam(value="chk", defaultValue="on") String chk) {
		
		// 1:1 문의 페이징
	    EzenPaging ezenPaging = new EzenPaging(page1, 10, this.ar.getQuestionCountByAll(1), 5); // 유저정보 강제 입력 1 대신 (principal.getName() 넣기
	    List<CsQuestion> csQuestion = this.ar.getUserByKeyword(1, ezenPaging.getStartNo(), ezenPaging.getPageSize()); // 유저정보 강제 입력 1 대신 (principal.getName() 넣기
		
	    
	 // 공지사항 페이징
	    EzenPaging ezenPaging2 = new EzenPaging(page2, 10, this.ans.getNoticeCountAll(), 5);
	    List<Notice> noticeList = ans.getNoticePaging(ezenPaging2.getStartNo(), ezenPaging2.getPageSize());
	    
	 // 현재 활성화된 페이지 타입 정보 추가
	    model.addAttribute("activePageType", pageType);
	    
		return "csc/csPage";
	}
	
	// 문의글 상세
	//@PreAuthorize("isAuthenticated()")
	@GetMapping("/detail/{id}")
	public String csDetail(Model model, @PathVariable("id") Integer id, Principal principa) throws UserException {
		CsQuestion csDetail = this.qr.getQuestion(id);
		model.addAttribute("csQuestion", csDetail);
		
		return "csc/csDetail";
		
		
		
		
	}
	
	
	

}
