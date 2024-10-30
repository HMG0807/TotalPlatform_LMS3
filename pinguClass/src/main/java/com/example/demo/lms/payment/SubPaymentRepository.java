package com.example.demo.lms.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.lms.entity.SubPayment;

public interface SubPaymentRepository extends JpaRepository<SubPayment, Integer> {

}
