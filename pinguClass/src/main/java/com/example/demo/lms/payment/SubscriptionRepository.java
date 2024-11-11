package com.example.demo.lms.payment;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.lms.entity.Subscription;
import com.example.demo.lms.entity.User;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

	Subscription findByUser(User user);
}
