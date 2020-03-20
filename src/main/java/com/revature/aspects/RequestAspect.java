package com.revature.aspects;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class RequestAspect {
	
	Logger log = Logger.getRootLogger();
	
	@Before("within(com.revature.controllers.*)")
	public void logRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		log.info(request.getMethod() + " request made to: " + request.getRequestURI());
	}
	
//	@Around("within(com.revature.controllers.*)")
//	public String authorizeRequest(ProceedingJoinPoint jp) throws Throwable {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//		if(request.getHeader("Authorization")!=null) {
//			log.info("authorized");
//			return (String) jp.proceed();
//		} else {
//			log.warn("unauthorized");
//			return "You are unauthorized to make this request";
//		}
//	}

}
