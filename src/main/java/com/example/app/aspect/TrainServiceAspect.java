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
public class TrainServiceAspect {

    Logger logger = LoggerFactory.getLogger(TrainServiceAspect.class);

    @Pointcut("execution(* com.example.app.service.TrainService.*(..)) ")
    private void anyTrainService() {}


    @Before("anyTrainService()")
    public void beforeAdvice(JoinPoint joinPoint)
    {
        logger.info("Start operation: " + joinPoint.getSignature());
    }

    @After("anyTrainService()")
    public void afterAdvice(JoinPoint joinPoint)
    {
        logger.info("End operation: " + joinPoint.getSignature());
    }
}
