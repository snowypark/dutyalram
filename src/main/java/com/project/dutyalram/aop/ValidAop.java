package com.projectnmt.dutyalram.aop;

import com.projectnmt.dutyalram.dto.OAuth2SignupReqDto;
import com.projectnmt.dutyalram.dto.SignUpReqDto;
import com.projectnmt.dutyalram.exception.ValidException;
import com.projectnmt.dutyalram.repository.UserMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Aspect
public class ValidAop {
    @Autowired
    private UserMapper userMapper;
    @Pointcut("@annotation(com.projectnmt.dutyalram.aop.annotation.ValidAspect)")
    private void pointCut(){}

    @Around("pointCut()")
    public Object around (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        BeanPropertyBindingResult bindingResult = null;
        for(Object arg : args) {
            if(BeanPropertyBindingResult.class == arg.getClass()) {
                bindingResult = (BeanPropertyBindingResult) arg;
            }
        }
        if(methodName.equals("signup")) {
            SignUpReqDto SignUpReqDto = null;
            for(Object arg : args) {
                if(SignUpReqDto.class == arg.getClass()) {
                    SignUpReqDto = (SignUpReqDto) arg;
                }

            }
            if(userMapper.findUserByUsername(SignUpReqDto.getUsername()) != null) {
                ObjectError objectError = new FieldError("username", "username", "이미 존재하는 사용자이름입니다.");
                bindingResult.addError(objectError);
            }
        }

        if(methodName.equals("oAuth2Signup")) {
            OAuth2SignupReqDto oAuth2SignupReqDto = null;
            for(Object arg : args) {
                if(OAuth2SignupReqDto.class == arg.getClass()) {
                    oAuth2SignupReqDto = (OAuth2SignupReqDto) arg;
                }
            }
            if(userMapper.findUserByUsername(oAuth2SignupReqDto.getUsername()) != null) {
                ObjectError objectError = new FieldError("username", "username", "이미 존재하는 사용자이름입니다.");
                bindingResult.addError(objectError);
            }
        }
        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                String fieldName = fieldError.getField();
                String message = fieldError.getDefaultMessage();
                errorMap.put(fieldName, message);
            }
            throw new ValidException(errorMap);
        }


        return proceedingJoinPoint.proceed();
    }
}
