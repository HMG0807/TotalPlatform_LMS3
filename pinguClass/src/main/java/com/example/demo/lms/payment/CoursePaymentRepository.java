package com.example.demo.lms.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.lms.entity.CoursePayment;

public interface CoursePaymentRepository extends JpaRepository<CoursePayment, Integer> {

	
}
