package com.example.demo.lms.LoginCheck;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;



/**
 * HandlerMethod  : 실행될 핸들러(컨트롤러의 메소드) loginCheck가 null이라면 로그인 없이 접근가능한 핸들러이므로 true 리턴
 */

@Component
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {
	
	private final LoginCheckService loginCheckService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler)
        throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginCheck loginCheck = handlerMethod.getMethodAnnotation(LoginCheck.class);	
        
        
        
        if (loginCheck == null) {
            return true;
        }
        
        if (loginCheckService.checkToken(request) == null) {
            throw new undefinedUserException("로그인 후 다시 이용해 주세요");
        }
        return true;

    }
}