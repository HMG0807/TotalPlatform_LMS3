package com.example.demo.lms.mypage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Lecture;

public interface InstUseLectureRepository extends JpaRepository<Lecture, Integer> {

	/********************************** 강좌 ID로 QnA 문의글 조회 **********************************/
	@Query(value = "SELECT * FROM lecture where course_id = :id and delete_yn = 'n' limit :start, :idx", nativeQuery = true)
	List<Lecture> findLectureByCourseId(@Param("id") Integer courseId, @Param("start") int startNo, @Param("idx") int pageSize);
	
	@Query(value = "SELECT count(*) FROM lecture where course_id = :id and delete_yn = 'n'" , nativeQuery = true)
	int countLectureByCourseId(@Param("id") Integer courseId);
}
