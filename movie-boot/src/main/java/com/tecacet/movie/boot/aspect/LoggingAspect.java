package com.tecacet.movie.boot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	@Around("within(com.tecacet.movie.jpa..*)")
	public Object logActivity(ProceedingJoinPoint joinPoint) throws Throwable {
		Object returnValue = joinPoint.proceed();
		String method = joinPoint.getSignature().toShortString();
		System.err.println("Executing " + method);
		return returnValue;
	}
}
