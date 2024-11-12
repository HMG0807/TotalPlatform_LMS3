package com.example.demo.lms.community;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.CommunityComment;
import com.example.demo.lms.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommunityCommentService {
	
	private final CommunityCommentRepository communityCommentRepository;
	
	
	// 1110 커뮤니티 댓글 상세 조회 - 이순
	public CommunityComment getdetail(Integer id) throws UserException {
		Optional<CommunityComment> communityComment = this.communityCommentRepository.findById((id));
		if(communityComment.isPresent()) {
			return communityComment.get();
		}else {
			throw new UserException("데이터를 찾을수 없습니다");
		}
	}
	
	

	public void commentCreate(Community community, String content, User user) {
		
		// 댓글 작성
		CommunityComment commentCreate = new CommunityComment();
		
		commentCreate.setCommunity(community);
		commentCreate.setContent(content);
		commentCreate.setUser(user);
		commentCreate.setDeleteYn("n");
		commentCreate.setLastUpdate(LocalDateTime.now());
		
		this.communityCommentRepository.save(commentCreate);
		
	}
	
	// 댓글 수정
	public void commentModify(CommunityComment commentModify, String content) {
		commentModify.setContent(content);
		commentModify.setLastUpdate(LocalDateTime.now());
				
		this.communityCommentRepository.save(commentModify);
				
	}
	
	
	
	
	
	
	// 댓글 삭제
	public void delete(CommunityComment communityCommentDelete) {
		
		communityCommentDelete.setDeleteYn("y");
		this.communityCommentRepository.save(communityCommentDelete);
		
	}


	
	// 1110 커뮤니티 댓글 상세 조회 - 이순
//		public CommunityComment getdetail(Integer id) throws UserException {
//			Optional<CommunityComment> communityComment = this.communityCommentRepository.findById((id));
//			if(communityComment.isPresent()) {
//				return communityComment.get();
//			}else {
//				throw new UserException("데이터를 찾을수 없습니다");
//			}
//		}

	
	// 1110 커뮤니티 댓글 상세 조회 - 이순
	public CommunityComment getdetail(Community community) throws UserException {

		Optional<CommunityComment> communityComment = this.communityCommentRepository.findById(community.getCmId());
		if(communityComment.isPresent()) {
			return communityComment.get();
		}else{
			throw new UserException("데이터를 찾을 수 없습니다");
		}
		
	}


// 11/11 이순 수정중
//	public CommunityComment getList(Integer id) {
//		
//		// 커멘트에서 외래키로 리스트 조회
//		List<CommunityComment> commentList = this.communityCommentRepository.findByCmId(id);
//
//		return null ;
//	}
	
	
	
	
	
	







}
