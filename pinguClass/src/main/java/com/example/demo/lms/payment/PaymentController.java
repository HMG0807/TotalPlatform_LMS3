package com.example.demo.lms.payment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.lms.entity.Subscribe;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PaymentController {

	private final SubPaymentService spr;
	private final CoursePaymentService cpr;
	private final subscribeService sr;
	
	
//	구독 결제 페이지 이동
	@GetMapping("/subPaymentPage")
	public String subPaymentPage() {
		
		return "payment/subscriptionPage"; 
	
	}
	

// 구독 결제 상품 선택 시, 구매창으로 이동
	@GetMapping(value = "/subPaymentOrder/{id}")
	public String subscriptionPayment(Model model,
			@PathVariable("id") Integer id) {
		
		Subscribe s = this.sr.getSubscribe(id);
		model.addAttribute("Subscribe", s);
		return "/payment/subPaymentOrder";
	}
	
	
}
