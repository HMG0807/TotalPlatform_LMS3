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
	
	private final CsQuestionRepository qr;
	private final CsQuestionUserRepository userRepository;
	
	// a팀 코드 참고
	// 1:1 문의 내용 가져오기
			public CsQuestion getQuestion(Integer csQuestionId) throws UserException {
				Optional<CsQuestion> q1 = this.qr.findById(csQuestionId);
				if(q1.isPresent()) {
					return q1.get();
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

	
	
	
	
	
//	// 문의글 조회
//	public List<CsQuestion> getList(){
//		
//		return this.qr.findAll();
//		
//	}
//	
//	
//	
//	//로그인한 유저의 모든 고객센터 문의글을 조회하는 메소드
//		public Page<CsQuestion> getList(int page, String id)  throws UserException {
//			List<Sort.Order> sorts = new ArrayList<>();
//			sorts.add(Sort.Order.desc("lastUpdate"));
//
//			Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
//			
//			return this.qr.findByUser(getUser(id), pageable);
//		}
//		
//		
//		
//		// 문의글 상세
//		public CsQuestion getQuestion(Integer id) throws UserException {
//			Optional<CsQuestion> q1 = this.qr.findById(id);
//			if(q1.isPresent()) {
//				return q1.get();
//			}else {
//				throw new UserException("데이터를 찾을수 없습니다");
//			}
//		}
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
//		//유저 정보 가져오기
		public User getUser(String id) throws UserException {
			Optional<User> user = this.userRepository.findById(id);
			
			if(user.isPresent()) { 
				return user.get();
			} else {
				throw new UserException("유저 정보가 없습니다.");
			}
		}
	
	
	
	

}
