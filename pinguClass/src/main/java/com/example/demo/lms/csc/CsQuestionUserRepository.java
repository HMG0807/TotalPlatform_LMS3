package com.example.demo.lms.csc;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.lms.entity.User;



public interface CsQuestionUserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findById(String id);

}
