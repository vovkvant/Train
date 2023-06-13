package com.example.app.aspect;

import com.example.app.service.ScheduleService;
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
public class ScheduleServiceAspect {

    Logger logger = LoggerFactory.getLogger(ScheduleServiceAspect.class);

    @Pointcut("execution(* com.example.app.service.ScheduleService.*(..)) ")
    private void anyScheduleService() {}


    @Before("anyScheduleService()")
    public void beforeAdvice(JoinPoint joinPoint)
    {
        logger.info("Start operation: " + joinPoint.getSignature());
    }

    @After("anyScheduleService()")
    public void afterAdvice(JoinPoint joinPoint)
    {
        logger.info("End operation: " + joinPoint.getSignature());
    }
}
