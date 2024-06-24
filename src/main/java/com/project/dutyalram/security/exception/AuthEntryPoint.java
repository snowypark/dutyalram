package com.projectnmt.dutyalram.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {//필터에서 사용하는 컨트롤러 역활

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //보통 예외의 종류당 대응을 조건문으로 처리
        System.out.println(authException);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}
