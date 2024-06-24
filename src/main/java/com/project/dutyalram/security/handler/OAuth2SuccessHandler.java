
package com.projectnmt.dutyalram.security.handler;

import com.projectnmt.dutyalram.entity.User;
import com.projectnmt.dutyalram.jwt.JwtProvider;
import com.projectnmt.dutyalram.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Value("${server.deploy-address}")
    private String serverAddress;
    @Value("${server.port}")
    private String port;
    @Value("${client.deploy-address}")
    private String clientAddress;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String name = authentication.getName();
        System.out.println(authentication)  ;
        User user = userMapper.findUserByOAuth2name(name);
        if(user == null) {
        //oauth2 로그인을 통해 회원가입을 진행한 기록이 없는 상태
            DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
            String providerName = oAuth2User.getAttribute("provider").toString();
            response.sendRedirect("http://"+ clientAddress + "/auth/oauth2/signup?name=" + name + "&provider=" + providerName);
            return;
        }
        //oauth2 로그인을 통해 회원가입을 진행한 기록이 있는 상태
        String accessToken = jwtProvider.generateToken(user);
        response.sendRedirect("http://" + clientAddress + "/auth/oauth2/signin?accessToken=" + accessToken);
        //oauth2 동기화

    }
}

