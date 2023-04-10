package com.example.springbootinterceptor.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    // 处理指定接口
    private static final String TOKEN_LOGIN = "/user/token";
    private static final String USER_NO_URL = "/user/no"; // 拦截不存在的接口路径

    // 不要要校验的接口
    private static final List<String> NO_NEED_LOGIN = new ArrayList<>(Arrays.asList("/user/health", "/logout"));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取访问的接口名
        String uri = request.getRequestURI();
        String contentPath = request.getContextPath();
        if (uri.startsWith(contentPath + "/")) {
            // 去掉contextPath
            uri = uri.replace(contentPath + "/", "/");
        }
        if (NO_NEED_LOGIN.contains(uri)) {
//            log.info("资源{}不需要登录，直接访问", uri);
            System.out.println("这个接口不需要验证，直接放行.");
            return true;
        }
        if (uri.equals(TOKEN_LOGIN)) {
            response.setStatus(200);
            response.setCharacterEncoding("utf-8");
            response.getWriter().println("处理指定接口，并返回数据信息."); // 可以返回相关json处理
            return false;
        }
        if (uri.equals(USER_NO_URL)) {
            response.setStatus(500);
            response.setCharacterEncoding("utf-8");
            response.getWriter().println("拦截的是未定义的接口."); // 可以返回相关json处理
            return false;
        }

        // 可以对每个接口进行身份校验 或者 编写相关业务逻辑
        System.out.println("执行了拦截器的preHandle方法");
        return true;
    }
}
