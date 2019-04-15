package com.springboot.sample.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 对系统service类的切面逻辑处理
 */
@Aspect
@Component
public class ServiceAspectConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceAspectConfig.class);

    //切点定义
    @Pointcut("within(com.springboot.sample.service.*Service)")
    public void servicePointCut(){}

    //advice处理
    @Around("servicePointCut()")
    public Object around(ProceedingJoinPoint point){
        LOG.info("spring aop around-before");
        Object object = new Object();
        try {
            object = point.proceed();
        }catch (Throwable e){
            LOG.info("spring aop around-afterthrow");
        }finally {
            LOG.info("spring aop around-after");
        }
        LOG.info("spring aop around-afterreturn");
        return object;
    }

    @After("servicePointCut()")
    public void after(){
        LOG.info("spring aop after");
    }

    @AfterReturning("servicePointCut()")
    public void afterReturning(){
        LOG.info("spring aop return");
    }

    @AfterThrowing("servicePointCut()")
    public void afterThrowing(){
        LOG.info("spring aop throw");
    }
}
