package com.example.demo.lms.mypage;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.lms.Authuser.Authuser;
import com.example.demo.lms.LoginCheck.LoginCheck;
import com.example.demo.lms.community.CommunityService;
import com.example.demo.lms.community.UserException;

import com.example.demo.lms.courseQna.QnaService;
import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.CommunityComment;
import com.example.demo.lms.entity.Course;
import com.example.demo.lms.entity.Qna;
import com.example.demo.lms.entity.QnaAnswer;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.paging.EzenPaging;
import com.example.demo.lms.user.UserRepository;
import com.example.demo.lms.user.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageController {
	// 마이페이지에서 사용자 관리를 처리하는 컨트롤러.
	
	private final CommunityService communityService;
	private final UserRepository userRepository;
	private final QnaService qnaService;
	private final UserService userService;
	@GetMapping("/mypage/community")
    public String getMyPageCommunityList(
        @RequestParam(name = "page", defaultValue = "0") int page,  // 페이지 번호, 기본값은 0
        Model model  // 뷰로 데이터를 전달하는 모델 객체
    ) throws UserException {
		
		String userId = "dongmony";       // 로그인 기능 완료시 실제 유저 아이디 가져와야함.
		
		
		//EzenPaging ezenPaging = new EzenPaging(현재 페이지 번호, 페이지당 글 갯수, 총 글 갯수, 페이징 버튼 갯수)
	    EzenPaging ezenPaging = new EzenPaging(page, 10, communityService.getCommunityCountById(userId), 5);
	    List<Community> communityList = this.communityService.getCommunityListByUser(userId, ezenPaging.getStartNo(), ezenPaging.getPageSize());
	    List<CommunityComment> commentList = this.communityService.getCommentsByUser(userId, ezenPaging.getStartNo(), ezenPaging.getPageSize());
	    
	    model.addAttribute("communityList", communityList);
	    model.addAttribute("commentList", commentList);
	    model.addAttribute("page", ezenPaging);
	    
	    return "mypage/communityManage";
	}

	
	
	// 마이페이지에서 글 제목 클릭시 상세 페이지로 이동-> 해당 글 정보 가져와서 화면에 출력 
	@GetMapping("/mypage/community/{cmId}")
	public String viewCommunityDetail(@PathVariable("cmId")Integer cmId, Model model) throws UserException {
	    // cmId에 해당하는 커뮤니티 글 정보를 가져옴
	    Community community = communityService.getdetail(cmId);
	    
	    // 모델에 커뮤니티 정보 추가
	    model.addAttribute("community", community);
	    
	    // 상세보기 화면 반환
	    return "mypage/communityDetail";
	}
	
	@GetMapping("/mypage/community/edit/{cmId}")
	public String editCommunityForm(@PathVariable("cmId") Integer cmId, Model model) throws UserException {
	    // cmId에 해당하는 커뮤니티 글 정보를 가져옴
	    Community community = communityService.getdetail(cmId);
	    
	    
	    // 모델에 커뮤니티 정보 추가
	    model.addAttribute("community", community);
	    
	    // 수정 폼 화면 반환
	    return "mypage/communityEdit";
	    
	    
	    
	}
	
	@PostMapping("/mypage/community/edit/{cmId}")
	public String editCommunity(@PathVariable("cmId") Integer cmId,    // 각각 pathVariable, 
								@RequestParam("title") String title,   // RequestParam 안 파라미터는 로그인 기능 완료시 지워줘도 됨 
								@RequestParam("content") String content) throws UserException {
	    
	    // 커뮤니티 글 업데이트
	    communityService.updateCommunity(cmId, title, content);
	    
	    // 수정 후 상세보기 페이지로 리다이렉트
	    return "redirect:/mypage/community/" + cmId;
	}
	
	
	
	
	
	
	
	
	// 마이페이지에서 커뮤니티 작성한 글 삭제 기능 담당 해당 글의 cmId(글번호)를 받아서 삭제 기능 
	@PostMapping("/mypage/community/delete/{cmId}")
	public String deleteCommunity(@PathVariable("cmId") Integer cmId) throws UserException {
	    // 커뮤니티 글 삭제
	    communityService.deleteCommunity(cmId);
	    
	    // 삭제 후, 목록 페이지로 리다이렉트
	    return "redirect:/mypage/community";
	}
	
	
	

	
	// 마이페이지 강좌 QnA 목록 조회 
	@GetMapping("/mypage/qna")
	public String getMyPageQnaList(
	    @RequestParam(name = "page", defaultValue = "0") int page,
	    Model model
	) {
		Integer userId = 17;  // 로그인 기능 완료시 실제 유저 아이디 가져와야함

	    EzenPaging ezenPaging = new EzenPaging(page, 10, qnaService.getQnaCountByUser(userId), 5);
	    List<Qna> qnaList = qnaService.getQnaListByUser(userId, ezenPaging.getStartNo(), ezenPaging.getPageSize());

	    model.addAttribute("qnaList", qnaList);
	    model.addAttribute("page", ezenPaging);

	    return "mypage/userMypageQnaList";
	}
	
	
	
	
	// QnA 상세 페이지 조회 , 강사 답변 조회 
	@GetMapping("/mypage/qna/{qnaId}")
	public String viewQnaDetail(@PathVariable("qnaId") Integer qnaId, Model model) {
	    
	    // QnA 상세 조회
	    Qna qna = qnaService.getQnaDetail(qnaId);
	    if (qna == null) {
	        throw new RuntimeException("QnA not found");
	    }
	    model.addAttribute("qna", qna);
	    
	    // 해당 QnA의 모든 답변 조회 (페이징 없이)
	    List<QnaAnswer> qnaAnswers = qnaService.getAllQnaAnswers(qnaId);
	    
	    // 디버깅용 로그 출력
	    System.out.println("답변 리스트: " + qnaAnswers);
	    
	    // 답변이 없는 경우에도 빈 리스트로 처리
	    if (qnaAnswers == null || qnaAnswers.isEmpty()) {
	        model.addAttribute("qnaAnswers", new ArrayList<>());  // 빈 리스트 전달
	    } else {
	        model.addAttribute("qnaAnswers", qnaAnswers);
	    }
	    
	    return "mypage/userMypageQnaDetail";
	}

	 
	

//	// QnA 등록 페이지로 이동
//    @GetMapping("/mypage/qna/new")
//    public String showQnaForm(Model model) {
//        model.addAttribute("qna", new Qna());
//        model.addAttribute("courses", qnaService.getAllCourses()); // 강좌 목록 추가
//        // 모든 강좌 목록을 가져오는 부분 제거
//        return "CourseQna/CourseQnaregistration";
//    }
//
//    // QnA 저장 
//    @PostMapping("/mypage/qna/save")
//    public String saveQna(@ModelAttribute Qna qna, @RequestParam Integer courseId) {
//        qnaService.saveQnaWithCourse(qna, courseId); // courseId를 받아서 처리
//        return "redirect:/mypage/userMypageQnaList";
//    }
//	
//	
	

	// QnA 수정 메서드 
	@GetMapping("/mypage/qna/edit/{qnaId}")
	public String editQnaForm(@PathVariable("qnaId") Integer qnaId, Model model) {
	    Qna qna = qnaService.getQnaDetail(qnaId);
	    model.addAttribute("qna", qna);
	    return "courseQna/CourseQnaEdit";
	}

	
	@GetMapping("/mypage")
	public String mypageTest() {
		
		return "mypage/myPage";
	}
	
	
	// QnA 수정 처리 
	@PostMapping("/mypage/qna/edit/{qnaId}")
	public String editQna(
	    @PathVariable("qnaId") Integer qnaId,
	    @RequestParam("title") String title,
	    @RequestParam("content") String content
	) {
		
	    qnaService.updateQna(qnaId, title, content);
	    return "redirect:/mypage/qna/" + qnaId;
	}
	
	
	// QnA 삭제 
	@PostMapping("/mypage/qna/delete/{qnaId}")
	public String deleteQna(@PathVariable("qnaId") Integer qnaId) {
	    qnaService.deleteQna(qnaId);
	    return "redirect:/mypage/qna";
	}
	
	// QnA 강사가 댓글을 달면 조회가 가능 . 
	
	
	
	/////////////////////////////////회원정보, 구독, 쿠폰 관련//////////////////
	
	///////회원 정보 수정//////
	
	@GetMapping("/mypage/edit")
	public String editUserInfo() {
		return "mypage/myPageUserEdit";
	}
	
	@GetMapping("/mypage/edit/password")
	public String editUserPassword() {
		return "mypage/myPageEditPassword";
	}
	
	//////////////////구독상황///////////////////
	
	@GetMapping("/mypage/subscription")
	public String mySubscription() {
		return "mypage/myPageSubscription";
	}
	
	
	
	//////////////쿠폰현황///////////////////////////
	
	@LoginCheck
	@GetMapping("/mypage/coupon")
	public String myCoupon(@Authuser User user, Model model) {
		model.addAttribute("user", user);
		return "mypage/myPageCoupon";
	}
}
	
	
	

