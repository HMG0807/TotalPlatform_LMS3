package com.example.demo.lms.community;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.User;

public interface CommunityRepository extends JpaRepository<Community, Integer> {

	// 커뮤니티 미삭제 글 조회
	@Query(value = "SELECT * FROM community c WHERE c.delete_yn = 'n'",   nativeQuery = true)
	List<Community> findAll();
	
	Page<Community> findByUser(User user, Pageable pageable);


}
