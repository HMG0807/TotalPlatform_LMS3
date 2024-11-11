package com.example.demo.lms.registration;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Registration;
import com.example.demo.lms.entity.User;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
	
	
	/*마이페이제이 - 수강 강좌 내역 조회 남동현*/
	    @Query(value = "SELECT * FROM registration WHERE user_id = :userId ORDER BY create_date DESC LIMIT :start, :size", nativeQuery = true)
	    List<Registration> findLimitStartIdx(@Param("userId") String userId, @Param("start") int start, @Param("size") int size);

	    @Query(value = "SELECT COUNT(*) FROM registration WHERE user_id = :userId", nativeQuery = true)
	    int countRegistrationByUser(@Param("userId") String userId);
	}