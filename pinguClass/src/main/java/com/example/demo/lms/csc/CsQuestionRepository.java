package com.example.demo.lms.csc;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.CsQuestion;
import com.example.demo.lms.entity.User;

public interface CsQuestionRepository extends JpaRepository<CsQuestion, Integer>{
	
	
	Page<CsQuestion> findAll(Pageable pageable);
	Page<CsQuestion> findByTitleContaining(String keyword, Pageable pageable);
	
	@Query("select distinct cs " 
            + "from CsQuestion cs " 
            + "inner join cs.user u " 
            + "where cs.title like %:kw% " 
            + "or u.id like %:kw%")
	Page<CsQuestion> findAllByTitleOrId(@Param("kw") String kw, Pageable pageable);
	
	@Query("select distinct cs " 
            + "from CsQuestion cs " 
            + "where cs.title like %:kw%")
	Page<CsQuestion> findAllByTitle(@Param("kw") String kw, Pageable pageable);
	
	@Query("select distinct cs " 
            + "from CsQuestion cs " 
            + "inner join cs.user u " 
            + "where u.id like %:kw%")
	Page<CsQuestion> findAllById(@Param("kw") String kw, Pageable pageable);
	
	
	Page<CsQuestion> findByUser(User user, Pageable pageable);
	
	
	
	// a팀 코드 참고
	/* 유저 기본키로 1:1 문의글 조회 */
	@Query(value = "SELECT count(*) FROM cs_question WHERE user_id = :id" , nativeQuery = true)
	int countQuestionByAll(@Param("id") Integer userId);
	
	@Query(value = "SELECT * FROM cs_question WHERE user_id = :id limit :start, :idx", nativeQuery = true)
	List<CsQuestion> findQestionByUserId(@Param("id") Integer userId, @Param("start") int startNo, @Param("idx") int pageSize);
	
	

}
