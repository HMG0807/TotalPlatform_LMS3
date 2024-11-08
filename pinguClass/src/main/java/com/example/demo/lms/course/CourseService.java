package com.example.demo.lms.course;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.lms.entity.Course;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CourseService {

	private final CourseRepository courseRepository;
	
	
//	카테고리에서 강좌 들어 가서 보기 위해 만듦 
	public Course getCourse(Integer courseId) {
		
		Optional<Course> course = this.courseRepository.findById(courseId);
		
		return course.get();
	}





//	public int getQuestionCountByKeyword(Integer corseId) {
//
//		return this.courseRepository.countQnaByKeyword(corseId);
//	}
//
}
