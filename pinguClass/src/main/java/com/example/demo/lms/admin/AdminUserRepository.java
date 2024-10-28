package com.example.demo.lms.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.lms.entity.User;


public interface AdminUserRepository extends JpaRepository<User, Integer> {

}
