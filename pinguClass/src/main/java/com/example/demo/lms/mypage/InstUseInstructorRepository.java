package com.example.demo.lms.mypage;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.lms.entity.Instructor;
import com.example.demo.lms.entity.User;

public interface InstUseInstructorRepository extends JpaRepository<Instructor, Integer> {

	Instructor findByUser(User user);
}
