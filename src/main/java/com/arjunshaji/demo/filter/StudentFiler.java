package com.arjunshaji.demo.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class StudentFiler implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String cookieValue = null;
        HttpServletRequest servletRequest1 = (HttpServletRequest) request;
        HttpServletResponse servletResponse1 = (HttpServletResponse) response;
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                cookieValue = cookie.getValue();
            }
        }
        if(cookieValue != null){
            filterChain.doFilter(request,response);
        } else {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("REDIRECTING TO LOGIN PAGE || ADMIN LOGIN REQUIRED.........");
        }
    }
}
