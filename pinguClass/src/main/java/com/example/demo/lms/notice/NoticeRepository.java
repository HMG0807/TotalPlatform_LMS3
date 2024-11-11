package com.example.demo.lms.notice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Notice;
import com.example.demo.lms.entity.User;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
	
	
	
	// 1108 공지 개수 카운트
	@Query(value = "SELECT count(*) FROM notice  WHERE delete_yn like 'n'",   nativeQuery = true)
	int countNoticeAll();

	// 1108 공지 보이는 개수 제한
	@Query(value = "SELECT * FROM notice WHERE delete_yn like 'n' limit :start, :idx", nativeQuery = true)
	List<Notice> countNoticeByLimit(@Param("start") int startNo, @Param("idx") int pageSize);
	

}
