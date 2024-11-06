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
}
