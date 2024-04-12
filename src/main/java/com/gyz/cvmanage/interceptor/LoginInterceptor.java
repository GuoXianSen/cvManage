package com.gyz.cvmanage.interceptor;

import com.gyz.cvmanage.pojo.Result;
import com.gyz.cvmanage.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 令牌验证
        String token = request.getHeader("Authorization");
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            return true; // 放行
        } catch (Exception e) {
            // throw new RuntimeException(e);
            response.setStatus(401);  // 未授权 校验失败
            return false; // 拦截
        }
        // return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
