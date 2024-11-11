package com.example.demo.lms.mypage;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.lms.Authuser.Authuser;
import com.example.demo.lms.LoginCheck.LoginCheck;
import com.example.demo.lms.community.CommunityService;
import com.example.demo.lms.community.UserException;
import com.example.demo.lms.course.CourseService;
import com.example.demo.lms.courseQna.QnaService;
import com.example.demo.lms.courseReview.courseReviewService;
import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.CommunityComment;
import com.example.demo.lms.entity.Course;
import com.example.demo.lms.entity.Qna;
import com.example.demo.lms.entity.QnaAnswer;
import com.example.demo.lms.entity.Registration;
import com.example.demo.lms.entity.Review;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.paging.EzenPaging;
import com.example.demo.lms.registration.RegistrationService;
import com.example.demo.lms.entity.Subscription;
import com.example.demo.lms.payment.SubscriptionService;
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
	private final CourseService courseService;
	private final UserService userService;
	private final RegistrationService registrationService;
	private final courseReviewService coursereviewService;
	private final SubscriptionService subscriptionService;

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

	 
	

	// QnA 등록 페이지로 이동
    @GetMapping("/mypage/qna/new")
    public String showQnaForm(Model model) {
        model.addAttribute("qna", new Qna());
        model.addAttribute("courses", qnaService.getAllCourses()); // 강좌 목록 추가
        
        // 모든 강좌 목록을 가져오는 부분 제거
        return "CourseQna/CourseQnaregistration";
    }

    
    
    // QnA 저장 
    @PostMapping("/mypage/qna/save")
    public String saveQna(@ModelAttribute Qna qna, @RequestParam(name = "courseId") Integer courseId) {
       
        Integer userId = 17; 
        User user = userService.findById(userId);
        qna.setUser(user);  // Qna 객체에 userId 설정
        
        
        
        Course course = courseService.findById(courseId);
        qna.setCourse(course);
        if (course != null) {
            qna.setCourse(course);
        } else {
            // Course가 없을 경우 처리 (에러 메시지 출력 또는 리다이렉트)
            return "redirect:/mypage/qna?error=courseNotFound";
        }
        
        
        
        // Qna 저장
        qnaService.saveQnaWithCourse(qna, courseId);
        return "redirect:/mypage/qna";
    }
	

	// QnA 수정 메서드 
	@GetMapping("/mypage/qna/edit/{qnaId}")
	public String editQnaForm(@PathVariable("qnaId") Integer qnaId, Model model) {
	    Qna qna = qnaService.getQnaDetail(qnaId);
	    model.addAttribute("qna", qna);
	    return "courseQna/CourseQnaEdit";
	}

	
	@GetMapping("/mypage")
	public String mypageTest() {
		
		return "redirect:/mypage/edit";
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
	
	
	@GetMapping("/mypage/courses")
	public String getEnrolledCourses(
	    @RequestParam(value = "page", defaultValue = "0") int page,
	    Model model
	) {
	    Integer userId = 17;  // 임시로 사용자 ID를 하드코딩

	    EzenPaging ezenPaging = new EzenPaging(page, 10, registrationService.countRegistrationByUser(userId), 5);
	    List<Registration> registrations = registrationService.getRegistrationsByUser(userId, ezenPaging.getStartNo(), ezenPaging.getPageSize());

	    model.addAttribute("registrations", registrations);
	    model.addAttribute("page", ezenPaging);

	    return "mypage/myenrolledCourses";
	}
	
	/////////////////////////////////회원정보, 구독, 쿠폰 관련//////////////////
	
	///////회원 정보 수정//////
	@LoginCheck
	@GetMapping("/mypage/edit")
	public String editUserInfo() {
		return "mypage/myPageUserEdit";
	}
	@LoginCheck
	@GetMapping("/mypage/edit/password")
	public String editUserPassword() {
		return "mypage/myPageEditPassword";
	}
	

	//////////////////구독상황///////////////////
	@LoginCheck
	@GetMapping("/mypage/subscription")
	public String mySubscription() {
		return "mypage/myPageSubscription";
	}
	
	
//=====================마이페이지 구독조회===============================//
	@LoginCheck
	@GetMapping("/mypage/subscription") 
	public String subscriptionStatus(Model model, @Authuser User user){
		
		Subscription subscription = subscriptionService.getUser(user.getUserId());
		
		model.addAttribute("subscription",subscription);
		
		return "/mypage/mySubscriptionStatus";
	} 
	
	//////////////쿠폰현황///////////////////////////
	
	

    @PostMapping("/courses/cancel/{rgId}")
    public String cancelRegistration(@PathVariable("rgId") int rgId) {
    	 System.out.println("수강 취소 요청, rgId: " + rgId);
        registrationService.cancelRegistration(rgId); // 수강 신청 내역 삭제
        return "redirect:/mypage/myenrolledCourses"; // 삭제 후 목록 페이지로 리다이렉트
    }
	
	

    // 마이페이지 내에서 수강중인 강좌 리뷰 조회
   

    @GetMapping("/mypage/CourseReview/list")
    public String listReviews(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Integer userId = 17; // 임시 사용자 ID
        EzenPaging ezenPaging = new EzenPaging(page, 10, coursereviewService.getUserReviewCount(userId), 5);
        List<Review> reviews = coursereviewService.getUserReviews(userId, ezenPaging.getStartNo(), ezenPaging.getPageSize());
        
        model.addAttribute("reviews", reviews);
        model.addAttribute("page", ezenPaging);
        return "mypage/myRegistrationreviewlist";
    }

    
    
    /*수강 강좌 작성 리뷰 모달 내에서 수정 */
    /*수정 버튼 클릭시 500 Internal Server Error 발생.  */
    @GetMapping("/mypage/CourseReview/get/{id}")
    @ResponseBody
    public ResponseEntity<Review> getReviewById(@PathVariable("id") Integer id) {    /*id 값을 url 경로에서 받아와 리뷰데이터 반환*/
        try {                      
            Review review = coursereviewService.getReviewById(id);              /*해당하는 리뷰를 가져오기*/
            if (review == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);          
            }
            return ResponseEntity.ok(review);         /*리뷰 데이터 존재하면 리뷰 데이터 반환*/
        } catch (Exception e) {
        	
        }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            
        }
    
    
    

    @PostMapping("/mypage/CourseReview/edit")
    public String updateReview(@ModelAttribute Review review) {
        coursereviewService.updateReview(review);
        return "redirect:/mypage/CourseReview/list";
    }
    
    
    
    
    

    @GetMapping("/mypage/CourseReview/delete/{id}")
    public String deleteReview(@PathVariable Integer id) {
        coursereviewService.deleteReview(id);
        return "redirect:/mypage/CourseReview/list";
    }

    @PostMapping("/mypage/CourseReview/delete")
    public String deleteReviewPost(@RequestParam("reviewId") Integer id) {
        coursereviewService.deleteReview(id);
        return "redirect:/mypage/CourseReview/list";
    }
    
    
    
    
	

	@LoginCheck
	@GetMapping("/mypage/coupon")
	public String myCoupon(@Authuser User user, Model model) {
		model.addAttribute("user", user);
		return "mypage/myPageCoupon";
	}

}
	
	
	

