package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static Logger log = Logger.getRootLogger();
	
	// successful executions
	@AfterReturning("within(com.revature.controllers.*)")
	public void logControllerAfterSuccess(JoinPoint jp) {
		log.info(jp.getSignature() + " - controller executed");
	}
	
	@AfterReturning("within(com.revature.services.*)")
	public void logServiceAfterSuccess(JoinPoint jp) {
		log.info(jp.getSignature() + " - service executed");
	}
	
	@AfterReturning("within(com.revature.models.*)")
	public void logModelAfterSuccess(JoinPoint jp) {
		log.info(jp.getSignature() + " - model executed");
	}
	
	@AfterReturning("within(com.revature.daos.*)")
	public void logDaoAfterSuccess(JoinPoint jp) {
		log.info(jp.getSignature() + " - dao executed");
	}
	
	// exceptions thrown when executing
	@AfterThrowing("within(com.revature.controllers.*)")
	public void logControllerAfterThrow(JoinPoint jp) {
		log.warn(jp.getSignature() + " - controller");
	}
	
	@AfterThrowing("within(com.revature.services.*)")
	public void logServiceAfterThrow(JoinPoint jp) {
		log.warn(jp.getSignature() + " - service");
	}
	
	@AfterThrowing("within(com.revature.models.*)")
	public void logModelAfterThrow(JoinPoint jp) {
		log.warn(jp.getSignature() + " - model");
	}
	
	@AfterThrowing("within(com.revature.controllers.*)")
	public void logDaoAfterThrow(JoinPoint jp) {
		log.warn(jp.getSignature() + " - controller");
	}

}
