package com.example.demo.lms.lecture;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Lecture;

public interface LectureRepository extends JpaRepository<Lecture,Integer> {

	@Query(value = "SELECT * from lecture where course_id = :id" , nativeQuery = true)
	List<Lecture> findByCourseId(@Param("id") Integer Id);
	
	
}
