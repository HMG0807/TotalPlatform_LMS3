package com.example.demo.lms.payment;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CoursePaymentService {

	private final CoursePaymentRepository cpr;
}
