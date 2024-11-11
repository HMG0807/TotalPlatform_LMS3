package com.example.demo.lms.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.lms.admin.AdminLectureRepository;
import com.example.demo.lms.entity.Course;
import com.example.demo.lms.entity.File;
import com.example.demo.lms.entity.Lecture;
import com.example.demo.lms.entity.LectureIndex;
import com.example.demo.lms.file.FileRepository;

import com.example.demo.lms.mypage.InstUseLectureIndexRepository;
import com.example.demo.lms.mypage.MyCourseMngService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CourseService {

	private final CourseRepository courseRepository;
	private final MyCourseMngService myCourseMngService; //한민기 추가
	private final AdminLectureRepository adminLectureRepository; //한민기 추가
	private final FileRepository fileRepository;
	
//	카테고리에서 강좌 들어 가서 보기 위해 만듦 
	public Course getCourse(Integer courseId) {
		
		Optional<Course> course = this.courseRepository.findById(courseId);
		
		return course.get();
	}


	public Course findById(Integer courseId) {
		 Optional<Course> course = courseRepository.findById(courseId);
	        return course.orElse(null);  // 값이 없으면 null .
	    }

	
	// 시간순으로 강의 조회
		public List<CourseDTO> getAllCourseBytime(Integer startIdx, Integer Idx) {
			
			List<Course> courseList =  this.courseRepository.findAll(startIdx, Idx);
			List<CourseDTO> dtoList = new ArrayList<>();
			
			for(int i=0; i<courseList.size(); i++) {
				
				CourseDTO dto = new CourseDTO();
				dto.setCourseId(courseList.get(i).getCourseId());
				dto.setTitle(courseList.get(i).getTitle());
				dto.setContent(courseList.get(i).getContent());;
				dto.setObjective(courseList.get(i).getObjective());
				dto.setPrice(courseList.get(i).getPrice());
				
				Integer flieId = courseList.get(i).getFileId();
				File file = this.fileRepository.findById(flieId).get();
				dto.setFile(file);
				
				dto.setLastUpdate(courseList.get(i).getLastUpdate());
				dto.setDeleteYn(courseList.get(i).getDeleteYn());
				dto.setInstructor(courseList.get(i).getInstructor());
				
				dtoList.add(dto);
				
				
			}
			return dtoList;
		}

		public int getCourseCount() {
			return this.courseRepository.countCourseAll(); 
		}






//	public int getQuestionCountByKeyword(Integer corseId) {
//
//		return this.courseRepository.countQnaByKeyword(corseId);
//	}
//
	
	/******************************************* 한민기 추가 ********************************************/
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 강좌 시청 클릭시 > 강의 목록 보기 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ LectureDTO 조회 : file_id -> file  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public LectureDTO getLectureDTO(Integer lectureId) {
		
		Lecture course = this.adminLectureRepository.findById(lectureId).get();
		
		LectureDTO dto = new LectureDTO();
		dto.setLectureId(lectureId);
		dto.setCourse(course.getCourse());
		dto.setTitle(course.getTitle());
		dto.setLectureOrder(course.getLectureOrder());
		dto.setObjective(course.getObjective());
		dto.setLastUpdate(course.getLastUpdate());
		dto.setDeleteYn(course.getDeleteYn());
		
		dto.setLectureIndexs(course.getLectureIndexs()); //목차 리스트
		dto.setFile(myCourseMngService.getFileName(course.getFileId()));
		
		return dto;
	}
	
	public Lecture getLecture2(Integer lectureId) {
		
		return this.adminLectureRepository.findById(lectureId).get();
	}
	
	
}
	





