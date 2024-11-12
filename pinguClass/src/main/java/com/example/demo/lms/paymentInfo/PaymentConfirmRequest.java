package com.example.demo.lms.paymentInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
public class PaymentConfirmRequest {
    private String paymentKey;
    private String orderId;
    private Long amount;
    
    public PaymentConfirmRequest(String paymentKey, String orderId, Long amount) {
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.amount = amount;
    }
}
	