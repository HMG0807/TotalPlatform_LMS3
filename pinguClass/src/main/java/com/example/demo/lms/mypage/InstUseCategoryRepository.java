package com.example.demo.lms.mypage;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.lms.entity.Category;

public interface InstUseCategoryRepository extends JpaRepository<Category, Integer> {

	Category findByCategory(String category);
}
