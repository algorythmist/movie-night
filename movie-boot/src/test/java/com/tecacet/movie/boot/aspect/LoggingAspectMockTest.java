package com.tecacet.movie.boot.aspect;

import static org.junit.Assert.assertEquals;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Test;
import org.mockito.Mockito;

public class LoggingAspectMockTest {

	@Test
	public void test() throws Throwable {
		LoggingAspect loggingAspect= new LoggingAspect();
		ProceedingJoinPoint joinPoint = Mockito.mock(ProceedingJoinPoint.class);
		Signature signature = Mockito.mock(Signature.class);
		Mockito.when(signature.toShortString()).thenReturn("methodical");
		Mockito.when(joinPoint.getSignature()).thenReturn(signature);
		Mockito.when(joinPoint.proceed()).thenReturn("OK");
		String returnValue = (String) loggingAspect.logActivity(joinPoint);
		assertEquals("OK", returnValue);
	}

}
