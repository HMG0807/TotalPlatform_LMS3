package com.example.demo.lms.user;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	// 1102 이순 : 이거 쓰신분 누구신가요... 사용한 이유 알려주세요....
	//@Query(value = "SELECT * FROM user where id like %:kw%", nativeQuery = true)
	//Optional<User> findById(@Param("kw")String username);

	// 로그인시 id 찾기
	@Query(value = "SELECT * FROM user where id=:username", nativeQuery = true)
	Optional<User> loginId(@Param("username") String username);
	Optional<User> findByName(String username);

	// 로그인한 유저 정보 찾기
	Optional<User> findById(String id);
	
	
	
}
