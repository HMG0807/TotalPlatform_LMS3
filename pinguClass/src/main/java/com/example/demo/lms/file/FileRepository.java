package com.example.demo.lms.file;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.lms.entity.File;

public interface FileRepository extends JpaRepository<File, Integer> {

}
