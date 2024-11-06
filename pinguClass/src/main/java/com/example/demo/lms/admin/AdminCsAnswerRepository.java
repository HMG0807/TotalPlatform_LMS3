package com.example.demo.lms.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.lms.entity.CsAnswer;

public interface AdminCsAnswerRepository extends JpaRepository<CsAnswer, Integer> {

}
