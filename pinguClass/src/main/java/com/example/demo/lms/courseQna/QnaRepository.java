package com.example.demo.lms.courseQna;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Qna;

public interface QnaRepository extends JpaRepository<Qna, Integer> {
	
	
	/*마이페이지 QnA 강좌 조회 필요한 쿼리 */
	@Query(value = "SELECT * FROM qna WHERE user_id = :userId AND delete_yn = 'N' ORDER BY last_update DESC LIMIT :start, :size", nativeQuery = true)
	List<Qna> findByUserIdOrderByLastUpdateDesc(@Param("userId") Integer userId, @Param("start") int startNo, @Param("size") int pageSize);

	@Query(value = "SELECT COUNT(*) FROM qna WHERE user_id = :userId AND delete_yn = 'N'", nativeQuery = true)
	int countByUserId(@Param("userId") Integer userId);

}
