package com.example.demo.lms.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Course;

public interface AdminCourseRepository extends JpaRepository<Course, Integer> {

	/********************************** 강좌명 또는 강의명으로 강좌 조회 **********************************/
	@Query(value = "SELECT distinct c.* FROM course c LEFT JOIN lecture l ON c.course_id = l.course_id"
				+ " WHERE c.title like %:kw% or l.title like %:kw% limit :start, :idx", nativeQuery = true)
	List<Course> findAllByKeyword(@Param("kw") String kw, @Param("start") int startNo, @Param("idx") int pageSize);
	
	@Query(value = "SELECT count(distinct c.course_id) FROM course c LEFT JOIN lecture l ON c.course_id = l.course_id"
				+ " WHERE c.title like %:kw% or l.title like %:kw%", nativeQuery = true)
	int countUserByKeyword(@Param("kw") String kw);
	
	
	/********************************** 강좌명으로 강좌 조회 **********************************/
	@Query(value = "SELECT * FROM course WHERE title like %:kw% limit :start, :idx", nativeQuery = true)
	List<Course> findAllByCourseName(@Param("kw") String kw, @Param("start") int startNo, @Param("idx") int pageSize);
	
	@Query(value = "SELECT count(*) FROM course WHERE title like %:kw%", nativeQuery = true)
	int countCourseByName(@Param("kw") String kw);
	
	
	/********************************** 강의명으로 강좌 조회 **********************************/
	@Query(value = "SELECT distinct c.* FROM course c LEFT JOIN lecture l ON c.course_id = l.course_id"
				+ " WHERE l.title like %:kw% limit :start, :idx", nativeQuery = true)
	List<Course> findAllByLectureName(@Param("kw") String kw, @Param("start") int startNo, @Param("idx") int pageSize);
	
	@Query(value = "SELECT count(distinct c.course_id) FROM course c LEFT JOIN lecture l ON c.course_id = l.course_id"
				+ " WHERE l.title like %:kw%", nativeQuery = true)
	int countLectureByName(@Param("kw") String kw);
	

}
