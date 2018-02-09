package com.akopiants.rest;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectHelper {
	private org.slf4j.Logger logger =  LoggerFactory.getLogger(this.getClass());

	@Before("execution(* com.akopiants.rest.ScriptServiceImpl.getScriptOutput(..))")
	public void before(JoinPoint joinPoint){
		logger.info("getScriptOutput() starting");
	}
}
