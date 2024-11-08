package com.example.demo.lms.courseQna;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Qna;
import com.example.demo.lms.entity.QnaAnswer;

public interface QnaAnswerRepository extends JpaRepository<QnaAnswer, Integer> {

	
	
	/*특정 강좌 QnA에 대한 모든 답변 조회 (연관된 QnA 객체로 조회한다는뜻?) -남동현-*/
	List<QnaAnswer> findByQna(Qna qna);
	
	
}
