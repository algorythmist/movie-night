package com.tecacet.movie.aspect;


import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

public class LoggingMethodInterceptorDemo {

    public String saySomething() {
        return "hello";
    }

    @Test
    public void test() {
        LoggingMethodInterceptorDemo demo = new LoggingMethodInterceptorDemo();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new LoggingMethodInterceptor());
        proxyFactory.setTarget(demo);

        LoggingMethodInterceptorDemo proxy = (LoggingMethodInterceptorDemo) proxyFactory.getProxy();
        proxy.saySomething();

    }

}
