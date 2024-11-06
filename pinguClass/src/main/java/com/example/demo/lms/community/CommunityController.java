package com.example.demo.lms.community;


import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.CommunityComment;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.user.UserException;
import com.example.demo.lms.user.UserService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/community")
@RequiredArgsConstructor
@Controller
public class CommunityController {
	
	private final CommunityService communityService;
	private final UserService userService;
	private final CommunityCommentService communityCommentService;
	
	//커뮤니티 목록
	@PreAuthorize(value = "isAuthenticated()")
	@GetMapping("/list") 
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page, Principal principal) throws UserException {
		List<Community> communityList= this.communityService.getList();
		model.addAttribute("communityList", communityList);
		
		
		return "community/communityList";
	}
	
	// 커뮤니티 검색(API 활용)
	
	
	
	
	// 커뮤니티 상세페이지 주소
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/detail/{id}") 
	public String detail(Model model , @PathVariable("id") Integer id, CommunityCommentForm communityCommentForm) throws UserException{
		Community community = this.communityService.getdetail(id);
		model.addAttribute("community",community);
		return "community/communityDetail";
	}
	
	// 커뮤니티 글 작성 주소
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create") 
	public String communityCreate(CommunityForm communityForm) {
		
		return "community/communityForm";
	}
	
	
	// 커뮤니티 글 작성 저장
	@PostMapping("/create") 
	public String boardQuestionCreate(@Valid CommunityForm communityForm, BindingResult bindingResult, Principal principal) throws Exception{
		if(bindingResult.hasErrors()) {
			return "community/communityForm"; //오류가 있다면 리파지토리저장 없이 기존의 데이터를 boardQuestion_form.html에 표시
		}
		User user = this.userService.getUser(principal.getName()); //글쓴이 정보를 보여주기 위한 코드
		this.communityService.communityCreate(communityForm.getTitle(),communityForm.getContent(),user);
		return "redirect:/community/list";
		//오류가 없다면 boardQuestionService의 boardQuestionCreate로 리파지토리에 값을 저장하고 FQA/문의 게시판 화면으로 넘어간다
	}
	
	
	
	// 커뮤니티 댓글 작성 저장
	@PostMapping("/detail/{id}") 
	public String createCommunityComment(Model model,@PathVariable("id") Integer id ,
			@RequestParam(value="content") String content,
			@Valid CommunityCommentForm CommunityCommentForm, BindingResult bindingResult,
			Principal principal) throws Exception {
		
		Community community = this.communityService.getdetail(id);
		
		User user = this.userService.getUser(principal.getName());
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("community",community);
			return "community/communityDetail"; 
			//오류가 있다면 리파지토리저장 x
		}
		
		
		this.communityCommentService.communityCommentcreate(community, CommunityCommentForm.getContent(), user);
		
		return String.format("redirect:/community/detail/%s", id);
		
	}
	
	
	// 커뮤니티 글 수정하기
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String CommunityModify(CommunityForm communityForm, @PathVariable("id") Integer id,
			Principal principal, Model model) {
		
		try {
			Community communityModify = this.communityService.getdetail(id);
			model.addAttribute("communityModify", communityModify);
			model.addAttribute("communityModify", communityModify);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "community/communityModify";
	}
	
	
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String CommunityModify(Model model, @PathVariable("id") Integer id,
			@RequestParam(value="content") String content,
			@RequestParam(value="title") String title,
			@Valid CommunityForm communityForm, BindingResult bindingResult,
			Principal principal) throws Exception {
		
		Community community = this.communityService.getdetail(id);
		
		User user = this.userService.getUser(principal.getName());
		
		if(bindingResult.hasErrors()) {
			return "community/communityModify";
		}
		
		try {
			this.communityService.modify(community, communityForm.getTitle(),
					communityForm.getContent());
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
		return String.format("redirect:/community/detail/%s", id);
	}
	
	
	
	
	// 커뮤니티 글 삭제
	@GetMapping("/delete/{id}")
	public String communityDelete(@PathVariable("id") Integer id) throws Exception {
		Community communityDelete = this.communityService.getdetail(id);
		this.communityService.delete(communityDelete);
		
		return "redirect:/community/list";
		
	}
	
	
	// 커뮤니티 댓글 수정하기
//	@GetMapping("/modify/{id}/modifyComment/{commetId}")
//	public String commentDelete(@PathVariable("id") Integer id, @PathVariable("commetId") Integer commentId,  Principal principal, Model model) {
//		try {
//			Community community = this.communityService.getdetail(id);
//			CommunityComment commentModify = this.communityCommentService.getId(commentId);
//			model.addAttribute("community", community);
//			model.addAttribute("community", community);
//			model.addAttribute("communityComment", commentModify);
//			model.addAttribute("communityComment", commentModify);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return String.format("redirect:/community/detail/%s", id);
//		
//	}
	
	
	// 커뮤니티 댓글 삭제하기
//	@GetMapping("/deletecomment/{id}")
//	public String communityCommentDelete(@PathVariable("id") Integer id) throws Exception {
//		CommunityComment communityCommentDelete = this.communityService.getdetail(id);
//		this.communityService.delete(communityDelete);
//		
//		return "redirect:/community/list";
	
//	@DeleteMapping("/posts/{id}/comments/{commentId}")
//	public String deleteCommnet(@PathVariable("id") Integer id, )
	
	
	

}
