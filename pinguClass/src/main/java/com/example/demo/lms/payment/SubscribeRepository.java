package com.example.demo.lms.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.lms.entity.Subscribe;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{
	Subscribe findByType(String type);
}
