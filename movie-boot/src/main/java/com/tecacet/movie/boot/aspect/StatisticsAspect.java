package com.tecacet.movie.boot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.tecacet.movie.boot.jmx.AccessStatistics;

@Aspect
@Component
public class StatisticsAspect {

	private final AccessStatistics accessStatistics;
	
	public StatisticsAspect(AccessStatistics accessStatistics) {
		super();
		this.accessStatistics = accessStatistics;
	}
	@Around("execution(* com.tecacet.movie.boot.controller.DatabaseController.populateData(..))")
	public Object logActivity(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object returnValue = joinPoint.proceed();
		//TODO String method = joinPoint.getSignature().toShortString();
		stopWatch.stop();
		long msecs = stopWatch.getTotalTimeMillis();
		accessStatistics.addSample(msecs);
		return returnValue;
	}
}
