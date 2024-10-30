package com.example.demo.lms.coupon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CouponController {
	private final CouponService couponService;
	
	@GetMapping("/coupon")
	public String createC() {
		return "main/coupontest";
	}
	
	@PostMapping("/coupon")
	public String create() throws Exception {
		couponService.createCoupon("user3");
		return "redirect:/coupon";
	}
}
