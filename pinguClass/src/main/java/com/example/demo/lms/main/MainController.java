package com.example.demo.lms.main;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import com.example.demo.lms.LoginCheck.LoginCheck;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {

	
//---------------------MAIN PAGE--------------------------
	@GetMapping("/main")
	public String mainpage() {

		return "main/main";
		}
		

		
	
	

}
