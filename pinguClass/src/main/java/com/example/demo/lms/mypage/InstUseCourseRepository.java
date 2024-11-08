package com.example.demo.lms.mypage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Course;

public interface InstUseCourseRepository extends JpaRepository<Course, Integer> {

	/********************************** 강사 ID로 강좌 조회 **********************************/
	@Query(value = "SELECT * FROM course where inst_id = :instId and delete_yn = 'n' limit :start, :idx", nativeQuery = true)
	List<Course> findCourseByInstId(@Param("instId") Integer instId, @Param("start") int startNo, @Param("idx") int pageSize);
	
	@Query(value = "SELECT count(*) FROM course where inst_id = :instId and delete_yn = 'n'" , nativeQuery = true)
	int countCourseByInstId(@Param("instId") Integer instId);
}
