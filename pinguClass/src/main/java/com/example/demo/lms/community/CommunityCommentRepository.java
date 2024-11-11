package com.example.demo.lms.community;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.CommunityComment;
/* 남동현 */
/* 사용자의*/
public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Integer> {
	 @Query(value = "SELECT COUNT(*) FROM community_comment WHERE user_id = :userId", nativeQuery = true)
	    int countByUserId(@Param("userId") Integer userId);

	 
	 
	 
	    @Query(value = "SELECT * FROM community_comment WHERE user_id = :userId ORDER BY last_update DESC LIMIT :start, :idx", nativeQuery = true)
	    List<CommunityComment> findAllByUserId(@Param("userId") Integer userId, @Param("start") int start, @Param("idx") int idx);


	}











