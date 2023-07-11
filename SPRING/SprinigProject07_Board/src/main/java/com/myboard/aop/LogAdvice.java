package com.myboard.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Log
@Aspect
@Component
public class LogAdvice {
	@Before("execution(* com.myboard.model.BoardService*.*(..))")
	public void logBefore() {
		log.info("=================");
		System.out.println("logBefore");
	}
	
	@Around("execution(* com.myboard.model.BoardService*.*(..))")
	public Object logTime(ProceedingJoinPoint pip) {
		long start = System.currentTimeMillis();
		Object result = null;
		log.info("Target :"+pip.getTarget());
		log.info("Param :"+Arrays.toString(pip.getArgs()));
		
		try {
			result = pip.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		log.info("start :"+ start);
		log.info("end :"+ end);
		log.info("TIME :"+ (end-start));
		System.out.println("TIME :"+(end-start));
		return result;
	}
	
	
}
