package com.example.demo.lms.mypage;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.lms.community.CommunityService;
import com.example.demo.lms.community.UserException;
import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.paging.EzenPaging;
import com.example.demo.lms.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageController {
	// 마이페이지에서 사용자 관리를 처리하는 컨트롤러.
	
	private final CommunityService communityService;
	private final UserRepository userRepository;
	
	@GetMapping("/mypage/community")
    public String getMyPageCommunityList(
        @RequestParam(name = "page", defaultValue = "0") int page,  // 페이지 번호, 기본값은 0
        Model model  // 뷰로 데이터를 전달하는 모델 객체
    ) throws UserException {
		
		String userId = "dongmony";       // 로그인 기능 완료시 실제 유저 아이디 가져와야함.
		
		//EzenPaging ezenPaging = new EzenPaging(현재 페이지 번호, 페이지당 글 갯수, 총 글 갯수, 페이징 버튼 갯수)
		EzenPaging ezenPaging = new EzenPaging(page, 10, communityService.getCommunityCountById(userId), 5);
		List<Community> communityList = this.communityService.getUserByKeyword(userId, ezenPaging.getStartNo(), ezenPaging.getPageSize());
		
		model.addAttribute("communityList", communityList);
		model.addAttribute("page", ezenPaging);
		
        // 사용자 검증
		/*
		 * User user = userRepository.findUserById(userId) .orElseThrow(() -> new
		 * UserException("User not found"));
		 * 
		 * // 커뮤니티 글 목록을 페이징하여 가져오기 Page<Community> communityPage =
		 * communityService.getMyPageCommunityList(page);
		 * 
		 * // 모델에 커뮤니티 페이지와 사용자 정보를 담아 뷰로 전달 model.addAttribute("communityPage",
		 * communityPage); model.addAttribute("user", user);
		 */

        // 마이페이지 커뮤니티 목록을 표시하는 HTML 뷰 반환
        return "mypage/communityManage";  // 
    }
	
	
	// 마이페이지에서 사용자가 작성한 글을 수정
	@GetMapping("/mypage/community/edit/{cmId}")
	public String editCommunityForm(@PathVariable Integer cmId, Model model) throws UserException {
	    Community community = communityService.getdetail(cmId);
	    model.addAttribute("community", community);
	    return "mypage/communityManage";
	}

	@PostMapping("/mypage/community/edit/{cmId}")
	public String editCommunity(@PathVariable Integer cmId, @RequestParam String title, @RequestParam String content) throws UserException {
	    communityService.updateCommunity(cmId, title, content);
	    return "redirect:mypage/communityManage";
	}

	
	
	
	
	
}
	
	
	

