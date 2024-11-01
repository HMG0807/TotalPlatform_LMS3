package com.example.demo.lms.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Notice;

public interface AdminNoticeRepository extends JpaRepository<Notice, Integer> {

	/* 커뮤니티 제목으로 커뮤니티 작성글 내역 조회 */
	@Query(value = "SELECT * FROM notice WHERE title like %:title% limit :start, :idx", nativeQuery = true)	
	List<Notice> findNoticeLimitStartIdx(@Param("title") String title, @Param("start") int start, @Param("idx") int pageSize);
	
	@Query(value = "SELECT count(*) FROM notice where title like %:kw%" , nativeQuery = true)
	int countNoticeByKeyword(@Param("kw") String kw);
	
	
	
}
