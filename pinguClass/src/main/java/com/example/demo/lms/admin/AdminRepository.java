package com.example.demo.lms.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.lms.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	Admin findByAdminCode(String adminCode);
}
