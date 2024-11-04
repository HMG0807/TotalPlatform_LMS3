package com.example.demo.lms.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	@GetMapping("/main")
	public String mainpage() {
		return "main/ecommerceMainPage";
		
	}
	
	

}
