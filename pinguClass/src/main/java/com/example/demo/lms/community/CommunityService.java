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
import com.example.demo.lms.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommunityService {
	
	private final CommunityRepository communityRepository;
//	private final UserRepository userRepository;

//	public List<Community> getList() {
//		// TODO Auto-generated method stub
//		return communityRepository.findAll();
//	}
//
//	public Community getdetail(Integer id) throws UserException {
//		Optional<Community> community = this.communityRepository.findById(id);
//		if(community.isPresent()) {
//			return community.get();
//		}else {
//			throw new UserException("데이터를 찾을수 없습니다");
//		}
//	}
	
	//로그인시 커뮤니티 글 조회
//		public Page<Community> getList(int page, String id)  throws UserException {
//			List<Sort.Order> sorts = new ArrayList<>();
//			sorts.add(Sort.Order.desc("cmId"));
//
//			Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
//			
//			return this.communityRepository.findByUser(getByUser(id), pageable);
//		}
//		
//		//유저 정보 가져오기
//		public User getByUser(String id) throws UserException {
//			Optional<User> user = this.userRepository.findById(id);
//			
//			if(user.isPresent()) { 
//				return user.get();
//			} else {
//				throw new UserException("유저 정보가 없습니다.");
//			}
//		}
		
		
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
		
	
	

}
