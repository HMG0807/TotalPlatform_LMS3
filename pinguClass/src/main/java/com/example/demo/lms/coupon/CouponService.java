package com.example.demo.lms.coupon;

import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.lms.entity.Coupon;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.user.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CouponService {
	
	private final CouponRepository couponRepository;
	private final UserService userService;
	
	public void createCoupon(String id) throws Exception {
		Coupon c = new Coupon();
		User u = this.userService.getUser(3);
		String code = RandomStringUtils.randomAlphabetic(16);
		
		while(true) {
			if(findByCode(code)==null) {
				break;
			}else {
				code = RandomStringUtils.randomAlphabetic(16);
			}
		}
		
		c.setCode(code);
		c.setUser(u);
		c.setCreateDate(LocalDateTime.now());
		c.setDiscount(3000);
		c.setUseYn("n");
		
		couponRepository.save(c);
	}
	
	public Coupon findByCode(String code) {
		return couponRepository.findByCode(code);
	}
	
	
	public boolean useCheck(String code) {
		Coupon c = couponRepository.findByCode(code);
		
		if(c.getUseYn()=="y") {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean existCoupon(String code) {
		Coupon c = couponRepository.findByCode(code);
		
		if(c == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void useCoupon(String code) {
		Coupon c = couponRepository.findByCode(code);
		c.setUseYn("y");
		couponRepository.save(c);
	}
}