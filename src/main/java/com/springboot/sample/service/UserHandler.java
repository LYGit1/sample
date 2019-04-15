package com.springboot.sample.service;

import com.springboot.sample.service.impl.UserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK原生代理，实现InvocationHandler接口，重写invoke方法
 */
public class UserHandler implements InvocationHandler {
    //定义日志成员变量，在代理方法时打印日志
    private static final Logger LOG = LoggerFactory.getLogger(UserHandler.class);
    //被代理的类，该类实现被代理接口方法
    private UserImpl userImpl;

    public UserHandler(UserImpl userImpl) {
        this.userImpl = userImpl;
    }

    /**
     * 在代理方法时，打印日志
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LOG.info("JDK proxy:"+method.getName());
        return method.invoke(userImpl,args);
    }
}
