package com.example.demo.lms.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.CsQuestion;


public interface AdminCsQuestionRepository extends JpaRepository<CsQuestion, Integer>{

	@Query(value = "SELECT * FROM cs_question limit :start, :idx", nativeQuery = true)	
	List<CsQuestion> findCsQuestionLimitStartIdx(@Param("start") int start, @Param("idx") int pageSize);
	
	@Query(value = "SELECT count(*) FROM cs_question" , nativeQuery = true)
	int countCsQuestionByKeyword();

	/* [페이징] 문의글 개수 조회 _ 이순 ----------------------------- */
	@Query(value = "SELECT count(*) FROM cs_question WHERE delete_yn = 'n'", nativeQuery = true)
	int countCsQuestionAll();

	/* [페이징] 현재 페이지에서 보이는 글 범위 지정 _ 이순 --------------------- */
	@Query(value = "SELECT * FROM cs_question where delete_yn = 'n' limit :start, :idx", nativeQuery = true)
	List<CsQuestion> findCsQuestionByLimit(@Param("start") int startNo, @Param("idx") int pageSize);
}
