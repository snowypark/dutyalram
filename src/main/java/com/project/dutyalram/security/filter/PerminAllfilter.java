
package com.projectnmt.dutyalram.security.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class PerminAllfilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        List<String> antMatchers = List.of("/error", "/auth", "/mail/authenticate","/oauth2","/login","/main","/comment", "/get", "/donator" ,"/info");

        String url = request.getRequestURI();
        request.setAttribute("isPermitAll", false);
        for(String antMatcher : antMatchers) {
            if(url.contains(antMatcher)) {
                request.setAttribute("isPermitAll", true);
            }
        }
        if(url.contains("/admin")) {
            request.setAttribute("isPermitAll", false);
        }

        filterChain.doFilter(request, response);
    }
}