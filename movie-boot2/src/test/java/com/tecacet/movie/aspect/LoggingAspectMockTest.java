package com.tecacet.movie.aspect;

import static org.junit.jupiter.api.Assertions.*;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LoggingAspectMockTest {

    @Test
    void logActivity() throws Throwable {
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