package com.example.demo.lms.csc;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.lms.entity.CsQuestion;

import lombok.RequiredArgsConstructor;

@RequestMapping("/csc")
@RequiredArgsConstructor
@Controller
public class CsController {
	
	private final CsQuestionService qr;
	
	
	// 1:1문의 목록
	@PreAuthorize("isAuthenticated()")
	@GetMapping()
	public String csCenter(Model model, @RequestParam(value="page", defaultValue="0") int page, Principal principa) {
		List<CsQuestion> csQuestion = this.qr.getList();
		model.addAttribute("csQuestion", csQuestion);
		
		return "csc/csPage";
	}
	
	
	

}
