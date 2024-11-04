package com.example.demo.lms.community;


import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/community")
@RequiredArgsConstructor
@Controller
public class CommunityController {
	
	private final CommunityService communityService;
	//private final UserService userService;
	
	//커뮤니티 목록
//	@PreAuthorize(value = "isAuthenticated()")
//	@GetMapping("/list") 
//	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page, Principal principal) throws UserException {
//		Page<Community> paging = this.communityService.getList(page, principal.getName());
//		model.addAttribute("paging", paging);
//		
//		
//		return "community/communityList";
//	}
	
//	//특정 질문 클릭시 보여주는 상세페이지 주소
//	@PreAuthorize("isAuthenticated()")
//	@GetMapping(value = "/detail/{id}") 
//	public String detail(Model model , @PathVariable("id") Integer id, CommunityCommentForm communityCommentForm) throws UserException{
//		Community community = this.communityService.getdetail(id);
//		model.addAttribute("community",community);
//		return "community_detail";
//	}
//	
	// 커뮤니티 글 작성 주소
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create") 
	public String communityCreate(CommunityForm communityForm) {
		
		return "community/communityForm";
	}
	
//	@PostMapping("/create") 
//	public String boardQuestionCreate(@Valid CommunityForm communityForm, BindingResult bindingResult, Principal principal) throws Exception{
//		if(bindingResult.hasErrors()) {
//			return "community/communityForm"; //오류가 있다면 리파지토리저장 없이 기존의 데이터를 boardQuestion_form.html에 표시
//		}
//		User user = this.userService.getUser(principal.getName()); //글쓴이 정보를 보여주기 위한 코드
//		this.communityService.communityCreate(communityForm.getTitle(),communityForm.getContent(),user);
//		return "redirect:/community/list";
//		//오류가 없다면 boardQuestionService의 boardQuestionCreate로 리파지토리에 값을 저장하고 FQA/문의 게시판 화면으로 넘어간다
//	}
	
	

}
