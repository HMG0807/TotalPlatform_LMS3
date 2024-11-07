package com.example.demo.lms.notice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.lms.admin.AdminUserRepository;
import com.example.demo.lms.csc.UserException;
import com.example.demo.lms.entity.Notice;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {
	
	private final NoticeRepository nr;
	private final AdminUserRepository ar;
	private final UserRepository ur;

	// 미삭제 공지 조회
	public List<Notice> getList(){
		
		return this.nr.findAll();
	}
	
	// 유저 정보 가져오기
	public User getUser(String id) throws UserException {
		Optional<User> user = this.ur.findById(id);
		if(user.isPresent()) { 
			return user.get();
		} else {
			throw new UserException("유저 정보가 없습니다.");
		}
	}

	public Notice getdetail(Integer id) throws UserException {
		Optional<Notice> notice = this.nr.findById(id);
		if(notice.isPresent()) {
			return notice.get();
		}else {
			throw new UserException("데이터를 찾을수 없습니다");
		}
		
	}

}
