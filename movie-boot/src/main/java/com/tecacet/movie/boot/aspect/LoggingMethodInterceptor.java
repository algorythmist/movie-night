package com.tecacet.movie.boot.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public class LoggingMethodInterceptor implements MethodInterceptor {

	private final Logger logger = LoggerFactory.getLogger("TIMING");
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object returnValue = invocation.proceed();
		stopWatch.stop();
		long msecs = stopWatch.getTotalTimeMillis();
		String method = invocation.getMethod().getName();
		logger.info("Method {} took {} milliseconds", method, msecs);
		return returnValue;
	}

}
