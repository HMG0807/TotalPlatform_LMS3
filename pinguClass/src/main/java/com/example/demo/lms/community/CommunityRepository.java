package com.example.demo.lms.community;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.User;

public interface CommunityRepository extends JpaRepository<Community, Integer> {

	List<Community> findAll();
	Page<Community> findByUser(User user, Pageable pageable);

	
	
	
	/*마이페이지 - 글 삭제하지 않는 글 조회 하는 메서드 - 남동현 */
	Page<Community> findByUserAndDeleteYn(User user, String deleteYn, Pageable pageable);
	
	
	/* ID 또는 이름으로 회원 조회 - 남동현 */
	@Query(value = "SELECT * FROM community WHERE user_id = :id limit :start, :idx", nativeQuery = true)
	List<Community> findAllById(@Param("id") Integer userId, @Param("start") int startNo, @Param("idx") int pageSize);
		
	@Query(value = "SELECT count(*) FROM community WHERE user_id = :id" , nativeQuery = true)
	int countUserById(@Param("id") Integer userId);
	
	
	
	
	
	
	
	
}
