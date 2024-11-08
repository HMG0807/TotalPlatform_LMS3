package com.example.demo.lms.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.lms.entity.Notice;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminNoticeService {
	
	private final AdminNoticeRepository anr;
	private final AdminRepository ar;

	//**************************고객센터 > 공지사항 총 count 조회***************************************************
		public int getNoticeCountAll() {
			return this.anr.countNoticeAll(); //유저정보 강제 입력함. 
		}
		
		//**************************고객센터 > 공지사항 count 조회***************************************************

		public List<Notice> getNoticePaging(int startNo, int pageSize) {
			return this.anr.findNoticeByAdminId(startNo, pageSize);
		}

}
