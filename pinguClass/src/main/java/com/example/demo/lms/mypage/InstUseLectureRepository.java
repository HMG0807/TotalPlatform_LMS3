package com.example.demo.lms.mypage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Lecture;

public interface InstUseLectureRepository extends JpaRepository<Lecture, Integer> {
	
	/********************************** 강좌 ID로 해당 강좌의 총 강의 갯수 반환 **********************************/
	@Query(value = "SELECT * FROM lecture where course_id = :id and delete_yn = 'n' order by lecture_order", nativeQuery = true)
	List<Lecture> findLectureByCourseId(@Param("id") Integer courseId);
	
	/********************************** 강좌 ID로 해당 강좌의 총 강의 갯수 반환 **********************************/
	@Query(value = "SELECT count(*) FROM lecture where course_id = :id and delete_yn = 'n'" , nativeQuery = true)
	int countLectureByCourseId(@Param("id") Integer courseId);
}
