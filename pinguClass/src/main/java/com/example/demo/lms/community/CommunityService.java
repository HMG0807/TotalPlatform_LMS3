package com.example.demo.lms.community;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.user.UserException;
import com.example.demo.lms.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommunityService {
	
	private final CommunityRepository communityRepository;
	private final UserRepository userRepository;

	//로그인시 커뮤니티 글 전체 조회(미삭제 글만)
	public List<Community> getList() {

		return communityRepository.findAll();
	}

	
	// 커뮤니티 글 상세 조회
	public Community getdetail(Integer id) throws UserException {
		Optional<Community> community = this.communityRepository.findById(id);
		if(community.isPresent()) {
			return community.get();
		}else {
			throw new UserException("데이터를 찾을수 없습니다");
		}
	}
	
	
//		public Page<Community> getList(int page, String id)  throws UserException {
//			List<Sort.Order> sorts = new ArrayList<>();
//			sorts.add(Sort.Order.desc("cmId"));
//
//			Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
//			
//			return this.communityRepository.findByUser(getByUser(id), pageable);
//		}
		
	
		//유저 정보 가져오기
		public User getByUser(String id) throws UserException {
			Optional<User> user = this.userRepository.findById(id);
			
			if(user.isPresent()) { 
				return user.get();
			} else {
				throw new UserException("유저 정보가 없습니다.");
			}
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
		
		public Community getcommunity() {
			return null;
			
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
			// 데이터 삭제
			//this.communityRepository.delete(communityDelete);
			
			// delete y/n 변경
			communityDelete.setDeleteYn("y");
			this.communityRepository.save(communityDelete);
			
			
			
		}
		
	
	

}
