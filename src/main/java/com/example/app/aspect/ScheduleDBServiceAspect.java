package com.example.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ScheduleDBServiceAspect {

    Logger logger = LoggerFactory.getLogger(ScheduleDBServiceAspect.class);

    @Pointcut("execution(* com.example.app.service.ScheduleDBService.*(..)) ")
    private void anyScheduleDBService() {}


    @Before("anyScheduleDBService()")
    public void beforeAdvice(JoinPoint joinPoint)
    {
        logger.info("Start operation: " + joinPoint.getSignature());
    }

    @After("anyScheduleDBService()")
    public void afterAdvice(JoinPoint joinPoint)
    {
        logger.info("End operation: " + joinPoint.getSignature());
    }
}
