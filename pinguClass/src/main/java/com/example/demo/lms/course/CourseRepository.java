package com.example.demo.lms.course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	
	@Query(value = "SELECT * FROM course c left join category ca on c.category_id = ca.category_id WHERE ca.category_id = :id limit :start, :idx", nativeQuery = true)
	List<Course> findByCategoryId(@Param("id") Integer categoryId);

	@Query(value = "SELECT count(*) FROM qna where course_id = :id" , nativeQuery = true)
	int countQnaByKeyword(@Param("id") Integer courseId);


	


}
