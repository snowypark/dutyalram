package com.projectnmt.dutyalram.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class paramsPrintAop {
    @Pointcut("@annotation(com.projectnmt.dutyalram.aop.annotation.ParamsPrintAspect)")
    private void pointCut(){}

    @Around("pointCut()")
    public Object around (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        CodeSignature codeSignature = (CodeSignature) proceedingJoinPoint.getSignature();
        String className = codeSignature.getDeclaringTypeName();
        String methodName = codeSignature.getName();
        String[] argName = codeSignature.getParameterNames();
        Object[] args = proceedingJoinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            log.info("{}: {} ({}.{})",argName[i], args[i], className, methodName);
        }

        return proceedingJoinPoint.proceed();
    }
}
