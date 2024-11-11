package com.example.demo.lms.lecture;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.lms.entity.Lecture;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureService {

	private final LectureRepository lectureRepository;
	
	
	public List<Lecture> getLecture(Integer Id){
		
		return this.lectureRepository.findByCourseId(Id);
		
	}
	
}
