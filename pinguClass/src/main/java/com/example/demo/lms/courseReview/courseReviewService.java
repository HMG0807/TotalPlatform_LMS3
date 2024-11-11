package com.example.demo.lms.courseReview;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.lms.course.CourseRepository;
import com.example.demo.lms.entity.Course;
import com.example.demo.lms.entity.Review;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.paging.EzenPaging;
import com.example.demo.lms.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class courseReviewService {

	private final UserRepository userRepository;
	private final CourseRepository courseRepository;
	private final CourseReviewRepository courseReviewRepository; 
	
	
	
	
	
		/*마이페이지 수강중인 강좌 리뷰 조회 - 남동현*/
	 public List<Review> getUserReviews(String userId, int startNo, int pageSize) {
	        return courseReviewRepository.findByUserWithPaging(userId, startNo, pageSize);
	    }

	    public int getUserReviewCount(String userId) {
	        return courseReviewRepository.countByUserId(userId);
	    }

	    /*수강중인 강좌 리뷰 조회 끝*/
	
	
	    /*수강중인 강좌 리뷰 수정, 삭제 - 남동현*/

	    public Review getReviewById(Integer id) {
	        return courseReviewRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Review not found"));
	    }

	    public void updateReview(Review updatedReview) {
	        Review existingReview = getReviewById(updatedReview.getReviewId());
	        existingReview.setTitle(updatedReview.getTitle());
	        existingReview.setContent(updatedReview.getContent());
	        existingReview.setRating(updatedReview.getRating());
	        existingReview.setLastUpdate(LocalDateTime.now());
	        courseReviewRepository.save(existingReview);
	    }

	    public void deleteReview(Integer id) {
	        courseReviewRepository.deleteById(id);
	    }
	
	
	
	
	    /*수강중인 강좌 리뷰 삭제 끝 - 남동현*/
	
	public void createReview(Review review, Integer courseId, Integer userId) {
	
	
		
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("해당 강의를 찾을 수 없습니다."));

        review.setUser(user);
        review.setCourse(course);
        review.setLastUpdate(LocalDateTime.now());
        review.setDeleteYn("N");

        courseReviewRepository.save(review);
    
		
		
		
		
		
	}

}
