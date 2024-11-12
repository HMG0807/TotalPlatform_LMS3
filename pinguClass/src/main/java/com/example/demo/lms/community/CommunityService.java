package com.example.demo.lms.community;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.CommunityComment;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommunityService {
	
	private final CommunityRepository communityRepository;
	private final UserRepository userRepository;
	private final CommunityCommentRepository communityCommentRepository;

	
	// 미삭제 커뮤 목록 조회
//	public List<Community> findByList() {
//		
//		return communityRepository.findAll();
//	}
//	
//	// 이순 : 임시 로그인 확인
//	
//	public Optional<User> loginCheck(String id){
//		
//		return userRepository.loginId(id);
//		
//	}
	

	// 1109 커뮤니티 상세 조회 - 이순
	public Community getdetail(Integer id) throws UserException {
		Optional<Community> community = this.communityRepository.findById(id);
		if(community.isPresent()) {
			return community.get();
		}else {
			throw new UserException("데이터를 찾을수 없습니다");
		}
	}
	
	//로그인시 커뮤니티 글 조회
		public Page<Community> getList(int page, String id)  throws UserException {
			List<Sort.Order> sorts = new ArrayList<>();
			sorts.add(Sort.Order.desc("cmId"));

			Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
			
			return this.communityRepository.findByUser(getByUser(id), pageable);
		}
	

		
		
		// 커뮤니티 글 작성하기
		public void communityCreate(String title ,String content, User user) {
			Community community = new Community();
			community.setTitle(title);
			community.setContent(content);
			community.setLastUpdate(LocalDateTime.now());
			community.setUser(user);
			community.setDeleteYn("n");
			this.communityRepository.save(community);
		}
		
		// 커뮤니티 글 수정하기
		public void modify(Community community, String title, String content) {
			community.setTitle(title);
			community.setContent(content);
			community.setLastUpdate(LocalDateTime.now());
					
			this.communityRepository.save(community);
					
		}

		// 커뮤니티 글 삭제
		public void delete(Community communityDelete) {
					
			// delete y/n 변경
			communityDelete.setDeleteYn("y");
			this.communityRepository.save(communityDelete);
							
		}
		
		
		//유저 정보 가져오기
		public User getByUser(String id) throws UserException {
			Optional<User> user = this.userRepository.findById(id);
			
			if(user.isPresent()) { 
				return user.get();
			} else {
				throw new UserException("유저 정보가 없습니다.");
			}
		}
		

	
		/* 마이페이지에서 특정 사용자의 삭제되지 않은 커뮤니티 글 목록을 페이징처리 - 남동현 */
		public Page<Community> getMyPageCommunityList(int page, String username) throws UserException {
			Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("lastUpdate")));

			// 사용자 검증
			User user = userRepository.findById(username).orElseThrow(() -> new UserException("User not found"));

			// 해당 사용자가 작성한 삭제되지 않은 커뮤니티 글 반환
			return communityRepository.findByUserAndDeleteYn(user, "N", pageable);
		}

		/* 유저ID로 커뮤니티 글의 총 갯수 조회 */
		public int getCommunityCountById(String userId) {

			User user = this.userRepository.findUserById(userId);

			return this.communityRepository.countUserById(user.getUserId());
		}
	
	

	public List<Community> getUserByKeyword(String userId, int startNo, int pageSize) {
		
		User user = this.userRepository.findUserById(userId);
		
		return this.communityRepository.findAllById(user.getUserId(), startNo, pageSize);
	}

	
	
	
	
	public void updateCommunity(Integer cmId, String title, String content)
	throws UserException {
		
		 	Community community = getdetail(cmId);
		    community.setTitle(title);
		    community.setContent(content);
		    community.setLastUpdate(LocalDateTime.now());
		    communityRepository.save(community);
		
		
	}

	////// 페이징 //////////////////////////////////
	// 모든 게시글 카운트
	public int getCommunityCount() {
		return this.communityRepository.countCommunityAll(); 
	}
	// 현재 페이지 게시글 정보, 언더바 몇페이지인지 구하기
	public List<Community> getCommunityByLimit(String kw, int startNo, int pageSize){
		return this.communityRepository.findCommunityByLimit(kw, startNo, pageSize);
		
	}
	

	// 커뮤니티 글 삭제 메서드
	public void deleteCommunity(Integer cmId) throws UserException {
		// 해당 커뮤니티 글을 찾고, 없으면 예외 발생
		Community community = communityRepository.findById(cmId)
				.orElseThrow(() -> new UserException("Community post not found"));
		// 삭제
		communityRepository.delete(community);

	}
    
    
    

    
	// 커뮤니티 댓글 조회 메서드

	public List<CommunityComment> getCommentsByUser(String userId, int startNo, int pageSize) {
		User user = this.userRepository.findUserById(userId);
		return communityCommentRepository.findAllByUserId(user.getUserId(), startNo, pageSize);
	}

	public List<Community> getCommunityListByUser(String userId, int startNo, int pageSize) {
		User user = this.userRepository.findUserById(userId);
		return this.communityRepository.findAllById(user.getUserId(), startNo, pageSize);
	}
    
    
    
    
    
    
	
			
	
		
}