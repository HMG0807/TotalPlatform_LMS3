package com.example.demo.lms.community;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.User;

public interface CommunityRepository extends JpaRepository<Community, Integer> {

	//List<Community> findAll();
	Page<Community> findByUser(User user, Pageable pageable);


}
