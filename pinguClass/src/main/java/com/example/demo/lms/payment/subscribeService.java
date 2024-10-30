package com.example.demo.lms.payment;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.lms.entity.Subscribe;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class subscribeService {

	private final subscribeRepository sr;

	public Subscribe getSubscribe(Integer id) {
		Optional<Subscribe> s1 = this.sr.findById(id);
		return s1.get();
		
	}
	
	
	
	
	
}
