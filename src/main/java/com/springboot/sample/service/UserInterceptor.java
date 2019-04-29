package com.springboot.sample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理，代理逻辑处理类
 */
public class UserInterceptor implements MethodInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(UserInterceptor.class);

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String info = String.format("cglib: %s",method.getName());
        LOG.info(info);
        return methodProxy.invokeSuper(o, objects);
    }
}
