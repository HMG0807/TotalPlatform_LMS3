package com.example.demo.lms.payment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.lms.admin.AdminService;
import com.example.demo.lms.entity.Subscribe;
import com.example.demo.lms.entity.Subscription;
import com.example.demo.lms.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscriptionService {

	private final SubscriptionRepository subscriptionRepository;
	private final AdminService adminService;
	
// ===================================구독권 적용=======================================	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 구독자 구독권 적용 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	public void apply(Subscribe subscribe,User user) {
		
		Subscription sub = new Subscription();
		sub.setStartDate(LocalDateTime.now());
		
		
		
		// 1. 구독권을 가지고 있을 때 (구독권 갱신을 위해 존재)
		if(user.getSubscription() != null) {
			sub = user.getSubscription();
			sub.setUser(user);
			sub.setType(subscribe.getType());
			
			if(subscribe.getType().equals("1개월 구독")) {
				sub.setEndDate(user.getSubscription().getEndDate().plus(1, ChronoUnit.MONTHS));}
		
			else if(subscribe.getType().equals("3개월 구독")) {
				sub.setEndDate(user.getSubscription().getEndDate().plus(3, ChronoUnit.MONTHS));}
			
			else if(subscribe.getType().equals("6개월 구독")) {
				sub.setEndDate(user.getSubscription().getEndDate().plus(6, ChronoUnit.MONTHS));}
				
			else if(subscribe.getType().equals("12개월 구독")) {
				sub.setEndDate(user.getSubscription().getEndDate().plus(12, ChronoUnit.MONTHS));}

		}

		
		// 2. 구독권을 가지고 있지 않을 때 (구독권 갱신을 위해 존재)
		else if(user.getSubscription() == null) {
			if(subscribe.getType().equals("1개월 구독")) {
				sub.setEndDate(LocalDateTime.now().plus(1, ChronoUnit.MONTHS));}
			
			else if(subscribe.getType().equals("3개월 구독")) {
				sub.setEndDate(LocalDateTime.now().plus(3, ChronoUnit.MONTHS));}
			
			else if(subscribe.getType().equals("6개월 구독")) {
				sub.setEndDate(LocalDateTime.now().plus(6, ChronoUnit.MONTHS));}
				
			else if(subscribe.getType().equals("12개월 구독")) {
					sub.setEndDate(LocalDateTime.now().plus(12, ChronoUnit.MONTHS));}

			sub.setUser(user);
			
			sub.setType(subscribe.getType());
		}
		
		
		
		this.subscriptionRepository.save(sub);
	}
	
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 구독자 정보 가져오기 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */

	public Subscription getUser(Integer userId) {
		
		User user2 = adminService.getUser(userId);
		
		Subscription user = this.subscriptionRepository.findByUser(user2);
		
		return user;
	}
}
