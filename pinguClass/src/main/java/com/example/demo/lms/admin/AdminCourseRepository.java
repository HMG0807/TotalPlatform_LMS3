package com.example.demo.lms.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.lms.entity.Course;

public interface AdminCourseRepository extends JpaRepository<Course, Integer> {

}
