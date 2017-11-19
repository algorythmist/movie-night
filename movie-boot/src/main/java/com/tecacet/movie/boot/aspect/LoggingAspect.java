package com.tecacet.movie.boot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {

	@Around("within(com.tecacet.movie.jpa..*)")
	public Object logActivity(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object returnValue = joinPoint.proceed();
		stopWatch.stop();
		long msecs = stopWatch.getTotalTimeMillis();
		String method = joinPoint.getSignature().toShortString();
		System.err.println(String.format("Method %s took %d milliseconds", method, msecs));
		return returnValue;
	}
}
