package com.example.demo.lms.mypage;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.lms.entity.LectureIndex;

public interface InstUseLectureIndexRepository extends JpaRepository<LectureIndex, Integer> {
	
}
