package com.example.demo.lms.user;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	@Query(value = "SELECT * FROM user where id like %:kw%", nativeQuery = true)
	//Optional<User> findById(@Param("kw")String username);
	Optional<User> findById(String username);
	
	@Query(value = "SELECT * FROM user where id = :id", nativeQuery = true)
	User findUserById(@Param("id") String userId);
}
