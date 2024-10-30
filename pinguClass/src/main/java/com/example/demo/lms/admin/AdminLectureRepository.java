package com.example.demo.lms.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Lecture;

public interface AdminLectureRepository extends JpaRepository<Lecture, Integer> {

	/* 강좌id로 강의 내역 조회 */
	@Query(value = "SELECT * FROM lecture WHERE course_id = :id limit :start, :idx", nativeQuery = true)
	List<Lecture> findLectureLimitStartIdx(@Param("id") Integer courseId, @Param("start") int start, @Param("idx") int pageSize);

	@Query(value = "SELECT count(*) FROM lecture WHERE course_id = :id", nativeQuery = true)
	int countLecture(@Param("id") Integer courseId);
	
}
