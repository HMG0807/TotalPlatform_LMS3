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
	
	private final NoticeRepository noticeRepository;

	
	
	
	///////// 페이징 ///////////////////////////
	// 1108 공지 개수 카운트 - 이순
	public int getNoticeCount() {
		
		return this.noticeRepository.countNoticeAll();
	}

	// 1108 공지 개수 카운트 - 이순
	public List<Notice> getNoticeByLimit(int startNo, int pageSize) {

		return this.noticeRepository.countNoticeByLimit(startNo, pageSize);
	}

	
	
	
	
	
	
	//////// 1109 공지 상세 조회 - 이순 //////////////////////////////
	public Notice getdetail(Integer id) throws UserException {
		
		Optional<Notice> notice = this.noticeRepository.findById(id);
		if(notice.isPresent()) {
			return notice.get();
		}else {
			throw new UserException("데이터를 찾을수 없습니다");
		}
		
	}

}
