package com.example.demo.lms.paymentInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.lms.Authuser.Authuser;
import com.example.demo.lms.entity.Subscribe;
import com.example.demo.lms.payment.SubscribeService;
import com.example.demo.lms.payment.SubscriptionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PaymentPageController {
	
	private final TossPaymentsApiClient tossPaymentsApiClient;
	private final PaymentInfoService paymentInfoService;
	private final SubscribeService subscribeService;
	
    @GetMapping("/payment2")
    public String paymentPage() {
        return "payment";
    }
    @GetMapping("/success")
    public String successPage(
        @RequestParam("paymentKey") String paymentKey,
        @RequestParam("orderId") String orderId,
        @RequestParam("amount") Long amount,
        Model model) {
        try {
            // 결제 승인 및 저장 처리
        	PaymentResponse response = paymentInfoService.processPaymentSuccess(
                    paymentKey,
                    orderId,
                    amount
                );
        	System.out.println(response.getOrderName());
        	Subscribe s = subscribeService.getSubscribeByType(response.getOrderName());
                model.addAttribute("payment", response);
                return "redirect:/subscriptionSuccess/"+s.getSubscId();
        } catch (Exception e) {
            // 에러 발생 시 실패 페이지로 리다이렉트
            return "redirect:/fail";
        }
    }
    @GetMapping("/fail")
    public String failPage() {
        return "/Payment/fail";
    }
}