package com.example.demo.lms.course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.demo.lms.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	
//	@Query(value = "SELECT * FROM course c left join category ca on c.category_id = ca.category_id WHERE ca.category_id = :id limit :start, :idx", nativeQuery = true)
//	List<Course> findByCategoryId(@Param("id") Integer categoryId);

	@Query(value = "SELECT count(*) FROM qna where course_id = :id" , nativeQuery = true)
	int countCourseByKeyword(@Param("id") Integer courseId);


	// 강좌 최신등록순 조회 (민준호)
	@Query(value = "select * from course order by last_update desc limit :start, :idx",  nativeQuery = true)
	List<Course> findAll(@Param("start") Integer startIdx, @Param("idx") Integer Idx);

	// 강좌 키워드로 조회 (민준호)
	@Query(value = "select * from course where title like %:kw%",  nativeQuery = true)
	List<Course> findByKeyword(@Param("kw") String kw);
	
	@Query(value = "SELECT count(*) FROM course WHERE delete_yn = 'n'", nativeQuery = true)
	int countCourseAll();
	

	
	@Query(value = "select c.* from course c join category ct on c.category_id = ct.category_id where c.category_id = :number",  nativeQuery = true)
	List<Course> findByCategoryId(@Param("number") Integer number);

}
