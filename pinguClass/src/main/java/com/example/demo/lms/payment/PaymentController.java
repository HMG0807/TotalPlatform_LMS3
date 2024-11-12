package com.example.demo.lms.payment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.lms.Authuser.Authuser;
import com.example.demo.lms.LoginCheck.LoginCheck;
import com.example.demo.lms.coupon.CouponService;
import com.example.demo.lms.course.CourseService;
import com.example.demo.lms.entity.Subscribe;
import com.example.demo.lms.entity.Subscription;
import com.example.demo.lms.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PaymentController {

	private final SubPaymentService spr;
	private final CoursePaymentService cpr;
	private final SubscribeService sr;
	private final SubscriptionService subscription; 
	private final CouponService couponService;
	
//	구독 결제 페이지 이동
	@GetMapping("/subPaymentPage")
	public String subPaymentPage() {
		
		return "/payment/subscriptionPage"; 
	
	}
	

// 구독 결제 상품 선택 시, 구매창으로 이동
	@GetMapping(value = "/subPaymentOrder/{id}")
	public String subscriptionPayment(Model model,
			@PathVariable("id") Integer id) {
		
		Subscribe subscribe = this.sr.getSubscribe(id);
		model.addAttribute("Subscribe", subscribe);
		return "/payment/subPaymentOrder";
	}

// 구독권 결제 완료 시, 결제 완료 창으로 이동 및 결제 데이터 전송
	@LoginCheck
	@GetMapping("/subscriptionSuccess/{id}")
	public String  subscriptionSuccess(Model model,
			@PathVariable("id") Integer id, @Authuser User user) throws Exception {
		
		Subscribe subscribe = this.sr.getSubscribe(id);
		
		this.subscription.apply(subscribe,user);

		if(subscribe.getSubscId() == 1) {
			this.couponService.createCoupon(user,1000);}
		else if(subscribe.getSubscId() == 2) {
			for(int i=0;i<6;i++) {
			this.couponService.createCoupon(user,2000);}}
		else if(subscribe.getSubscId() == 3) {
			for(int i=0;i<8;i++) {
			this.couponService.createCoupon(user,3000);}}
		else if(subscribe.getSubscId() == 4) {
			for(int i=0;i<10;i++) {
			this.couponService.createCoupon(user,5000);}}
		
		model.addAttribute("subscribe", subscribe);				
		
		return "/payment/subPaymentSuccess";
	}
	
	
	
}
