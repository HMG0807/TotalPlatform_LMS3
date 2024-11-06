package com.example.demo.lms.community;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.lms.entity.CommunityComment;

public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Integer> {

	

}
