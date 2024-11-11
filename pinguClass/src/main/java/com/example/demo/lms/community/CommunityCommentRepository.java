package com.example.demo.lms.community;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.CommunityComment;
/* 남동현 */
/* 사용자의*/
public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Integer> {
	
	/* 이순 : 미삭제 커뮤니티 댓글 조회*/
	@Query(value = "SELECT * FROM community_comment WHERE delete_yn = 'n'",  nativeQuery = true)
	List<CommunityComment> findAll();

	
	
	 @Query(value = "SELECT COUNT(*) FROM community_comment WHERE delete_yn = 'n' && user_id = :userId", nativeQuery = true)
	    int countByUserId(@Param("userId") Integer userId);

	 
	 
	 
	    @Query(value = "SELECT * FROM community_comment WHERE delete_yn = 'n' && user_id = :userId ORDER BY last_update DESC LIMIT :start, :idx", nativeQuery = true)
	    List<CommunityComment> findAllByUserId(@Param("userId") Integer userId, @Param("start") int start, @Param("idx") int idx);


	    
	    
	}











