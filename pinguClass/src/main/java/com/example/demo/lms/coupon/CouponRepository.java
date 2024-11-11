package com.example.demo.lms.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	@Query(value = "SELECT * FROM coupon where code like %:code% ", 
			  nativeQuery = true)
	Coupon findByCode(@Param("code")String code);
	
	@Query(value = "select count(*) from coupon c left join user u on  c.user_id = u.user_id where c.user_id = %:kw% " , nativeQuery = true)
	int countCouponByKeyword();
}
