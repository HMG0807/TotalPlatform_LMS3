package com.example.demo.lms.mypage;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.lms.entity.File;

public interface InstUseFileRepository extends JpaRepository<File, Integer> {

}
