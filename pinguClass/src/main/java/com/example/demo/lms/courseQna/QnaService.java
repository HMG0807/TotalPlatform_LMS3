package com.example.demo.lms.courseQna;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.demo.lms.course.CourseRepository;
import com.example.demo.lms.entity.Course;
import com.example.demo.lms.entity.Qna;
import com.example.demo.lms.entity.QnaAnswer;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.paging.EzenPaging;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaService {

	/*마이페이지 내가 강좌 QnA에 작성한 글 조회 */
	private final QnaRepository qnaRepository;;
	private final QnaAnswerRepository qnaanswerRepository;
	private final CourseRepository courseRepository;
	
	
	
	
	

	 public List<Qna> getQnaListByUser(Integer userId, int startNo, int pageSize) {
	        return qnaRepository.findByUserIdOrderByLastUpdateDesc(userId, startNo, pageSize);
	    }

	    public int getQnaCountByUser(Integer userId) {
	        return qnaRepository.countByUserId(userId);
	    }

	    public Qna getQnaDetail(Integer qnaId) {
	        return qnaRepository.findById(qnaId)
	                .orElseThrow(() -> new RuntimeException("QnA not found"));
	    }

	    public void updateQna(Integer qnaId, String title, String content) {
	        Qna qna = qnaRepository.findById(qnaId)
	                .orElseThrow(() -> new RuntimeException("QnA not found"));
	        qna.setTitle(title);
	        qna.setContent(content);
	        qna.setLastUpdate(LocalDateTime.now());
	        qnaRepository.save(qna);
	    }
	

    public void deleteQna(Integer qnaId) {
        Qna qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new RuntimeException("QnA not found"));
        qna.setDeleteYn("Y");
        qna.setLastUpdate(LocalDateTime.now());
        qnaRepository.save(qna);
    }

    public List<QnaAnswer> getAllQnaAnswers(Integer qnaId) {
        Qna qna = getQnaDetail(qnaId);
        return qnaanswerRepository.findByQna(qna);
    }

    public void saveQna(Qna qna) {
        qna.setLastUpdate(LocalDateTime.now());
        qna.setDeleteYn("N");
        qnaRepository.save(qna);
    }

    public void saveQnaWithCourse(Qna qna, Integer courseId) {
        Course course = getCourseById(courseId);
        qna.setCourse(course);
        qna.setLastUpdate(LocalDateTime.now());
        qna.setDeleteYn("N");
        qnaRepository.save(qna);
    }

    private Course getCourseById(Integer courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
    
    
    
    
    

    
  
