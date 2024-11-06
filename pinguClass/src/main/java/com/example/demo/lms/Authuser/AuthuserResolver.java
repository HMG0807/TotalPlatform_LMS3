package com.example.demo.lms.Authuser;

import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import com.example.demo.lms.LoginCheck.LoginCheckService;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.user.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
@RequiredArgsConstructor
public class AuthuserResolver implements HandlerMethodArgumentResolver {

		private final UserRepository userRepository;
		private final LoginCheckService loginCheckService;
		private final AuthuserService authuserService;
		
	    @Override
	    public boolean supportsParameter(MethodParameter parameter) {
	        boolean hasAnnotation = parameter.hasParameterAnnotation(Authuser.class);
	        boolean isUserType = User.class.isAssignableFrom(parameter.getParameterType());

	        return hasAnnotation && isUserType;
	    }
	    
	    
	
	    
	    
	    @Override
	    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
	        
	    	
	    	 HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
	         
	    	String jwtToken = loginCheckService.checkToken(servletRequest);
	    	ResponseEntity<String> user = authuserService.getNameWithHeader(jwtToken).block();
	    	List<String> listHeader = user.getHeaders().get("Principal");
	    	
	    	
	    	log.info(listHeader.get(0));

	    	String userId =  listHeader.get(0);
	    	
	        return userRepository.findById(userId).orElseThrow(
	                () -> new Exception(userId)
	        );
	    }
	    
	    
	    
	}

