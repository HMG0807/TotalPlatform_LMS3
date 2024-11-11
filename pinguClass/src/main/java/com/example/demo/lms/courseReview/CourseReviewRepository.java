package com.example.demo.lms.courseReview;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Review;

public interface CourseReviewRepository extends JpaRepository<Review, Integer>{

	 	@Query(value = "SELECT * FROM review r WHERE r.user_id = :userId ORDER BY r.last_update DESC LIMIT :start, :size", nativeQuery = true)
	    List<Review> findByUserWithPaging(@Param("userId") Integer userId, @Param("start") int startNo, @Param("size") int pageSize);

	    @Query(value = "SELECT COUNT(*) FROM review r WHERE r.user_id = :userId", nativeQuery = true)
	    int countByUserId(@Param("userId") Integer userId);
	    
	    
	    
	    
}
