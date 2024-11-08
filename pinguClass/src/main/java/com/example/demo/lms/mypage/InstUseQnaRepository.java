package com.example.demo.lms.mypage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Qna;

public interface InstUseQnaRepository extends JpaRepository<Qna, Integer> {

	/********************************** 강좌 ID로 QnA 문의글 조회 **********************************/
	@Query(value = "SELECT * FROM qna where course_id = :id and delete_yn = 'n' limit :start, :idx", nativeQuery = true)
	List<Qna> findQnaByCourseId(@Param("id") Integer courseId, @Param("start") int startNo, @Param("idx") int pageSize);
	
	@Query(value = "SELECT count(*) FROM qna where course_id = :id and delete_yn = 'n'" , nativeQuery = true)
	int countQnaByCourseId(@Param("id") Integer courseId);
}
