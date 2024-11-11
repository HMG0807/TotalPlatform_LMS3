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
import com.example.demo.lms.paging.EzenPaging;

import lombok.RequiredArgsConstructor;

@RequestMapping("/notice")
@RequiredArgsConstructor
@Controller
public class NoticeContoller {
	
	private final NoticeService noticeService;
	
	// 공지 목록
	//@PreAuthorize(value = "isAuthenticated()")
	@GetMapping("/list")
	public String notice(Model model, @RequestParam(value="page", defaultValue="0") int page) throws UserException {
		
		// 한 페이지에 글 10개씩, 언더바는 5페이지씩 보여줌 
		EzenPaging paging = new EzenPaging(page, 10, noticeService.getNoticeCount(), 5);
		// 현재 페이지 글 리스트, 언더바 몇페이지인지 계산 후 보여줌
		List<Notice> noticeList= this.noticeService.getNoticeByLimit(paging.getStartNo(), paging.getPageSize());
		
		// 공지 글 리스트 정보
		model.addAttribute("noticeList", noticeList);
		// 페이지 정보
		model.addAttribute("paging", paging);
		
		return "notice/noticePage";
	}
		
	// 공지 디테일
		//@PreAuthorize(value = "isAuthenticated()")
		@GetMapping("/detail/{id}")
		public String noticeDetail(Model model, @PathVariable("id") Integer id, Principal principal) throws UserException {
			Notice noticeDetail = this.noticeService.getdetail(id);
			model.addAttribute("noticeDetail", noticeDetail);
			
			return "notice/noticeDetail";
			
		}

}
