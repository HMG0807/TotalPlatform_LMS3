package com.example.demo.lms.user;

import lombok.Getter;
@Getter
public enum UserRole {
	
	ADMIN("ROLE-ADMIN"),
	USER("ROLE_USER");
	
	private String value;
	
	UserRole(String value){
		this.value = value;
	}
}