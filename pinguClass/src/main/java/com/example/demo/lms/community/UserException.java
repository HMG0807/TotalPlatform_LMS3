package com.example.demo.lms.community;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "객체 없음")
public class UserException extends Exception {
	
	public UserException(String msg) {
		super(msg);
	}
}
