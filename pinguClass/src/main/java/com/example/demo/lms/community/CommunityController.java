package com.example.demo.lms.community;


import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.user.UserForm;
import com.example.demo.lms.user.UserService;


import lombok.RequiredArgsConstructor;

@RequestMapping("/community")
@RequiredArgsConstructor
@Controller
public class CommunityController {
	
	private final CommunityService communityService;
	private final UserService userService;
	
	//커뮤니티 목록
	@PreAuthorize(value = "isAuthenticated()")
	@GetMapping("/list") 
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page, Principal principal) throws UserException {
		Page<Community> paging = this.communityService.getList(page, principal.getName());
		model.addAttribute("paging", paging);
		
		
		return "community/communityList";
	}
	
	//특정 질문 클릭시 보여주는 상세페이지 주소
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/detail/{id}") 
	public String detail(Model model , @PathVariable("id") Integer id, CommunityCommentForm communityCommentForm) throws UserException{
		Community community = this.communityService.getdetail(id);
		model.addAttribute("community",community);
		return "community_detail";
	}
	
	//FQA/문의 질문작성 주소
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create") 
	public String communityCreate(CommunityForm communityForm) {
		return "community_form";
	}
	
	

}
