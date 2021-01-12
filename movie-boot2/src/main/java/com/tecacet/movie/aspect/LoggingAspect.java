package com.tecacet.movie.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Log the time a method took to execute
 *
 * @author dimitri
 */
@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger("TIMING");

    @Around("execution(* com.tecacet.movie.jpa..*(..))")
    public Object logActivity(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object returnValue = joinPoint.proceed();
        stopWatch.stop();
        long msecs = stopWatch.getTotalTimeMillis();
        String method = joinPoint.getSignature().toShortString();
        logger.info("Method {} took {} milliseconds", method, msecs);
        return returnValue;
    }
}
