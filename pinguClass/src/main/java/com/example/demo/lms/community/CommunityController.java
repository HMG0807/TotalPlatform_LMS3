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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.lms.Authuser.Authuser;
import com.example.demo.lms.LoginCheck.LoginCheck;
import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.CommunityComment;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.paging.EzenPaging;
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
	
	//커뮤니티 목록 + 페이징 + 검색
	@LoginCheck
	@GetMapping("/list") 
	public String list(Model model,
			@RequestParam(value="kw", defaultValue="") String kw, 
			@RequestParam(value="page", defaultValue="0") int page,
			@Authuser User user) throws UserException {
	
		EzenPaging paging = new EzenPaging(page, 10, communityService.getCommunityCount(), 5);

		List<Community> communityList = this.communityService.getCommunityByLimit(kw, paging.getStartNo(), paging.getPageSize());
		model.addAttribute("communityList", communityList);
		model.addAttribute("paging", paging);
		
		return "community/communityList"; 
	}
	
	
	
	
	
	
	
	
	
	//특정 질문 클릭시 보여주는 상세페이지 주소
	@LoginCheck
	@GetMapping(value = "/detail/{id}") 
	public String detail(Model model , @PathVariable("id") Integer id,
			CommunityCommentForm communityCommentForm, @Authuser User user) throws UserException{
		// 질문 상세
		Community community = this.communityService.getdetail(id);
		model.addAttribute("community",community);
		
//		// 댓글 리스트
//		CommunityComment commentModify = this.communityCommentService.getList(id);
//		model.addAttribute("commentModify", commentModify);
		
		return "community/communityDetail";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// 커뮤니티 글 작성 주소
	@LoginCheck
	@GetMapping("/create") 
	public String communityCreate(CommunityForm communityForm, @Authuser User user) {
		
		return "community/communityForm";
	}
	
	@PostMapping("/create") 
	public String communityCreate(@Valid CommunityForm communityForm,
			BindingResult bindingResult, @Authuser User user) throws Exception{
		

		if(bindingResult.hasErrors()) {
			return "community/communityForm"; //오류가 있다면 리파지토리저장 없이 기존의 데이터를 boardQuestion_form.html에 표시
		}
		User userId = this.userService.getUser(user.getUserId()); //글쓴이 정보를 보여주기 위한 코드
		this.communityService.communityCreate(communityForm.getTitle(),communityForm.getContent(), userId);
		return "redirect:/community/list";
		//오류가 없다면 boardQuestionService의 boardQuestionCreate로 리파지토리에 값을 저장하고 FQA/문의 게시판 화면으로 넘어간다
	}
	
	
	
		
	
	
	
	
	
	
	
		
		// 커뮤니티 글 수정하기
		@LoginCheck
		@GetMapping(value="/modify/{id}")
		public String CommunityModify(CommunityForm communityForm, @PathVariable("id") Integer id,
				@Authuser User user, Model model) {
			
			try {
				Community communityModify = this.communityService.getdetail(id);
				model.addAttribute("communityModify", communityModify);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			return "community/communityModify";
		}
		
		
		
		@LoginCheck
		@PostMapping(value="/modify/{id}")
		public String CommunityModify(Model model, @PathVariable("id") Integer id,
				@RequestParam(value="content") String content,
				@RequestParam(value="title") String title,
				@Valid CommunityForm communityForm, BindingResult bindingResult,
				@Authuser User user) throws Exception {
			
			Community community = this.communityService.getdetail(id);
			
			
			
			if(bindingResult.hasErrors()) {
				return "community/communityModify";
			}
			
			try {
				User userId = this.userService.getUser(user.getUserId());
				this.communityService.modify(community, communityForm.getTitle(),
						communityForm.getContent());
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			
			return String.format("redirect:/community/detail/%s", id);
		}
		
		
		
		
		
		
		
		
		
		
		
		// 커뮤니티 글 삭제
		@LoginCheck
		@GetMapping(value="/delete/{id}")
		public String communityDelete(@PathVariable("id") Integer id, @Authuser User user) throws Exception {
			Community communityDelete = this.communityService.getdetail(id);
			this.communityService.delete(communityDelete);
			
			return "redirect:/community/list";
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
////////  댓글 //////////////////////////////////////////////////// 	
		
		// 커뮤니티 댓글 작성
		@LoginCheck
		@PostMapping(value="/detail/{id}") 
		public String createCommunityComment(Model model,@PathVariable("id") Integer id ,
				@RequestParam(value="content") String content,
				@Valid CommunityCommentForm CommunityCommentForm, BindingResult bindingResult,
				@Authuser User user) throws Exception {
					
			Community community = this.communityService.getdetail(id);
					
			//User userId = this.userService.getUser(user.getUserId()); // qwer -> principal.getName() 바꾸기
					
			if(bindingResult.hasErrors()) {
				model.addAttribute("community",community);
				return "community/communityDetail"; 
				//오류가 있다면 리파지토리저장 x
			}
					
					
			this.communityCommentService.commentCreate(community, CommunityCommentForm.getContent(), user);
					
			return String.format("redirect:/community/detail/%s", id);
					
		}
		
		
		
		
		
		
		
		
		
		// 커뮤니티 댓글 수정하기
//		@LoginCheck
//		@GetMapping(value="/commnetModify/{id}")
//		public String commentModify(@PathVariable("id") Integer id, @Authuser User user, Model model) {
//			try {
//				Community community = this.communityService.getdetail(id);
//				model.addAttribute("community", community);
//
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			return String.format("redirect:/community/detail/%s", id); // -> 질문의 id값으로 이동함
//			
//		}
		
		//@PreAuthorize("isAuthenticated()")
//				@PostMapping("/commnetModify/{id}")
//				public String CommunityModify(Model model, @PathVariable("id") Integer id,
//						@RequestParam(value="contents") String content,
//						@Valid CommunityCommentForm communityCommentForm, BindingResult bindingResult,
//						Principal principal) throws Exception {
//					
//					CommunityComment commentModify= this.communityCommentService.getdetail(id);
//					
//					
//					
//					if(bindingResult.hasErrors()) {
//						return "community/communityCommentModify";
//					}
//					
//					try {
//						User user = this.userService.getUser("qwer");
//						this.communityCommentService.commentModify(commentModify, content);
//							
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//						
//					}
//					
//					
//					return String.format("redirect:/community/detail/%s", id);
//				}
		
		
		
		
		
		
		
		

		
		
		
		// 커뮤니티 댓글 삭제하기
		@LoginCheck
		@GetMapping(value="/commentDelete/{id}")
		public String CommentDelete(@PathVariable("id") Integer id, @Authuser User user) throws UserException {
			CommunityComment commentDelete = this.communityCommentService.getdetail(id);
			this.communityCommentService.delete(commentDelete);
			
			return "redirect:/community/list";
			
	}
		
		
		

		
		
		
	
	

}
