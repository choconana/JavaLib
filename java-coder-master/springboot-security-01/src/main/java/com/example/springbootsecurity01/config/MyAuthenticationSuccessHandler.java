package com.example.springbootsecurity01.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功回调
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper mapper = new ObjectMapper();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // 返回json
//        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(mapper.writeValueAsString(authentication));

        // 页面跳转
        redirectStrategy.sendRedirect(request, response, "/index");

    }

}