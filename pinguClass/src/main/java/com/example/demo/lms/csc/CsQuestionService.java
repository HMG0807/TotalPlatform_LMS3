package com.example.demo.lms.csc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.CsQuestion;
import com.example.demo.lms.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CsQuestionService {
	
	private final CsQuestionRepository csQuestionRepository;
	private final CsQuestionUserRepository userRepository;
	
	// a팀 코드 참고
	// 1:1 문의 내용 가져오기
			public CsQuestion getDetail(Integer id) throws UserException {
				Optional<CsQuestion> csQiestion = this.csQuestionRepository.findById(id);
				if(csQiestion.isPresent()) {
					return csQiestion.get();
				}else {
					throw new UserException("데이터를 찾을수 없습니다");
				}
			}
			
			
			//유저 정보 가져오기
			public User getUser(Integer id) throws UserException {
				Optional<User> user = this.userRepository.findById(id);
				
				if(user.isPresent()) { 
					return user.get();
				} else {
					throw new UserException("유저 정보가 없습니다.");
				}
			}


			
			
			

		/// 페이징 ///////////////////////////////////////////
		// 모든 문의글 개수 구하기
		public int getQuestionCountByAll(Integer userId) {
			
			return this.csQuestionRepository.countQuestionByAll(userId);
		}

		// 현재 페이지에서 보이는 글 목록, 언더바 몇페이지인지 구하기
		public List<CsQuestion> getQuestionByLimit(Integer userId, int startNo, int pageSize) {
			
			return this.csQuestionRepository.findQestionByUserId(userId, startNo, pageSize);
			
			
		}


		// 문의글 작성
		public void create(String title, String content, User userId) {
			CsQuestion csQuestion = new CsQuestion();
			csQuestion.setTitle(title);
			csQuestion.setContent(content);
			csQuestion.setLastUpdate(LocalDateTime.now());
			csQuestion.setUser(userId);
			csQuestion.setDeleteYn("n");
			
			this.csQuestionRepository.save(csQuestion);
			
			
		}

		// 문의글 수정
		public void modify(CsQuestion csQuestion, String title, String content) {
			csQuestion.setTitle(title);
			csQuestion.setContent(content);
			csQuestion.setLastUpdate(LocalDateTime.now());
			
			this.csQuestionRepository.save(csQuestion);
		}
		
		
		// 문의글 삭제
		public void delete(CsQuestion csQuestion) {
			
			// delete y/n 변경
			csQuestion.setDeleteYn("y");
			this.csQuestionRepository.save(csQuestion);
							
		}
	
	
	

}
