package com.example.demo.lms.community;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.CommunityComment;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.user.UserException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityCommentService {
	
	private final CommunityCommentRepository communityCommentRepository;


	// 답글 저장
	public CommunityComment communityCommentcreate(Community community, String content, User user) {
		CommunityComment comment = new CommunityComment();
		comment.setCommunity(community);
		comment.setLastUpdate(LocalDateTime.now());
		comment.setContent(content);
		comment.setUser(user);
		comment.setDeleteYn("n");
		this.communityCommentRepository.save(comment);
		
		return comment;
		
		
	}

	// 한 댓글 id값 찾기
//	public CommunityComment getId(Integer id) throws UserException {
//		Optional<CommunityComment> comment = this.communityCommentRepository.findById(id);
//		if(comment.isPresent()) {
//			return comment.get();
//		}else {
//			throw new UserException("데이터를 찾을수 없습니다");
//		}
//			
//	}


		


}
