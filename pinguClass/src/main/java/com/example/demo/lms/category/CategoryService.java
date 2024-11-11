package com.example.demo.lms.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.lms.course.CourseDTO;
import com.example.demo.lms.course.CourseRepository;
import com.example.demo.lms.entity.Course;
import com.example.demo.lms.entity.File;
import com.example.demo.lms.file.FileRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {
	private final CourseRepository courseRepository;
	private final FileRepository fileRepository;
	
	
//	public List<Course> getCourseByKeyword() {
//
//		return this.courseRepository.findAll();
//	}
//	
	public List<CourseDTO> getAllCourseDTOList(){
		
		List<Course> courseList = this.courseRepository.findAll();
		
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
	
	
//	public List<CourseDTO> getCourseDTOList(Integer categoryId){
//		
//List<Course> courseList = this.courseRepository.findByCategoryId(categoryId);
//		
//		List<CourseDTO> dtoList = new ArrayList<>();
//		
//		for(int i=0; i<courseList.size(); i++) {
//			
//			CourseDTO dto = new CourseDTO();
//			dto.setCourseId(courseList.get(i).getCourseId());
//			dto.setTitle(courseList.get(i).getTitle());
//			dto.setContent(courseList.get(i).getContent());;
//			dto.setObjective(courseList.get(i).getObjective());
//			dto.setPrice(courseList.get(i).getPrice());
//			
//			Integer flieId = courseList.get(i).getFileId();
//			File file = this.fileRepository.findById(flieId).get();
//			dto.setFile(file);
//			
//			dto.setLastUpdate(courseList.get(i).getLastUpdate());
//			dto.setDeleteYn(courseList.get(i).getDeleteYn());
//			dto.setInstructor(courseList.get(i).getInstructor());
//			
//			dtoList.add(dto);
//			
//			
//		}
//		
//		return dtoList;
//		
//		
//	}
	
	public List<CourseDTO> getCourseByKeyWord(String kw){
		
		List<Course> courseList = this.courseRepository.findByKeyword(kw);
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
	
	public List<CourseDTO> getCourseByCategoryId(Integer number){
		
		List<Course> courseList = this.courseRepository.findByCategoryId(number);
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
	
	

}
