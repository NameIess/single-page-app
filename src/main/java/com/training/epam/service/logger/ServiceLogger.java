package com.training.epam.service.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
public class ServiceLogger {
    private static final Logger Log = LogManager.getLogger(ServiceLogger.class.getSimpleName());

    @Before("execution(* com.training.epam.service.impl.SkillServiceImpl.*(..))")
    public void logBeforeExecution(JoinPoint joinPoint) throws Throwable {
        Log.info("Method has been called : " + joinPoint.getSignature().getName());
        Log.info("Transferred parameters : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(
            pointcut = "execution(* com.training.epam.service.impl.SkillServiceImpl.*(..))",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) throws Throwable {
        Log.info("Executed method : " + joinPoint.getSignature().getName());
        Log.info("Transferred parameters : " + Arrays.toString(joinPoint.getArgs()));
        Log.info("Method returned value is : " + result);
    }

    @AfterThrowing(
            pointcut = "execution(* com.training.epam.service.impl.SkillServiceImpl.*(..))",
            throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        Log.error("Exception during method execution!");
        Log.error("Exception has been thrown within method : " + joinPoint.getSignature().getName());
        Log.error("Exception name : " + error);
        Log.error("Exception message : " + error.getMessage());
    }
}
