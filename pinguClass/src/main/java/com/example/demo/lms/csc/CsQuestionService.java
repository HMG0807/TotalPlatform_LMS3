package com.example.demo.lms.csc;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.lms.entity.CsQuestion;
import com.example.demo.lms.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CsQuestionService {
	
	private final CsQuestionRepository csQuestionRepository;
	private final CsQuestionUserRepository userRepository;
	
	// a팀 코드 참고함 _ 이순
	
	//유저 정보 가져오기
	public User getUser(Integer id) throws UserException {
		Optional<User> user = this.userRepository.findById(id);
				
		if(user.isPresent()) { 
			return user.get();
		} else {
			throw new UserException("유저 정보가 없습니다.");
		}
	}

			
		// 문의글 상세 _ 이순 
		public CsQuestion getDetail(Integer id) throws UserException {
			Optional<CsQuestion> csQuestion = this.csQuestionRepository.findById(id);
			if(csQuestion.isPresent()) {
				return csQuestion.get();
			}else {
				throw new UserException("데이터를 찾을수 없습니다");
			}
		}
//
//		public void create(String title, String contents, String id) throws UserException {
//			CsQuestion q = new CsQuestion();
//			q.setTitle(title);
//			q.setContent(contents);
//			q.setLastUpdate(LocalDateTime.now());
//			q.setUser(getUser(id));
//			this.qr.save(q);
//		}
//
//		public void modify(CsQuestion q, String title, String contents) {
//			q.setTitle(title);
//			q.setContent(contents);
//			this.qr.save(q);
//		}
//
//		public void delete(CsQuestion q) {
//			this.qr.delete(q);
//		}
//		
//		

		// 문의글 수정 _ 이순
		public void modify(CsQuestion csQuestion, String title, String contents) {
			csQuestion.setTitle(title);
			csQuestion.setContent(contents);
			csQuestion.setLastUpdate(LocalDateTime.now());
			
			this.csQuestionRepository.save(csQuestion);
			
		}

		// 문의글 삭제 _ 이순
		public void delete(CsQuestion csQuestionDelete) {
			csQuestionDelete.setDeleteYn("y");
			
			this.csQuestionRepository.save(csQuestionDelete);
			
		}

	
	
	
	

}
