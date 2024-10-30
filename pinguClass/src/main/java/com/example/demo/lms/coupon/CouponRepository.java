package com.example.demo.lms.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	@Query(value = "SELECT * FROM coupon where code like %:code% ", 
			  nativeQuery = true)
	Coupon findByCode(@Param("code")String code);
}
