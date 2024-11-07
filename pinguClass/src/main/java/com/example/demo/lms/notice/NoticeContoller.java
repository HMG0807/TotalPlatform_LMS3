package com.example.demo.lms.notice;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.lms.csc.UserException;
import com.example.demo.lms.entity.Notice;

import lombok.RequiredArgsConstructor;

//@RequestMapping("/notice")
@RequiredArgsConstructor
@Controller
public class NoticeContoller {
	
	private final NoticeService nr;
	
	// 공지 목록
		//@PreAuthorize(value = "isAuthenticated()")
		@GetMapping("/notice")
		public String notice(Model model, @RequestParam(value="page", defaultValue="0") int page, Principal principal) throws UserException {
			List<Notice> notice= this.nr.getList();
			model.addAttribute("notice", notice);
			
			return "notice/noticePage";
		}
		
	// 공지 디테일
		//@PreAuthorize(value = "isAuthenticated()")
		@GetMapping("/notice/detail/{id}")
		public String noticeDetail(Model model, @PathVariable("id") Integer id, Principal principal) throws UserException {
			Notice noticeDetail = this.nr.getdetail(id);
			model.addAttribute("notice", noticeDetail);
			
			return "notice/noticeDetail";
			
		}

}
