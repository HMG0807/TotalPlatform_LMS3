package com.example.demo.lms.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.lms.entity.Instructor;


public interface AdminInstructorRepository extends JpaRepository<Instructor, Integer> {

}
