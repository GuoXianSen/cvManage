package com.gyz.cvmanage.interceptor;

import com.gyz.cvmanage.pojo.Result;
import com.gyz.cvmanage.util.JwtUtil;
import com.gyz.cvmanage.util.ThreadLocalUtil;
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
            // System.out.println(claims);
            // 将业务信息存储到ThreadLocal中
            ThreadLocalUtil.set(claims);  // {id=5, username=test2}
            return true; // 放行
        } catch (Exception e) {
            // throw new RuntimeException(e);
            response.setStatus(401);  // 未授权 校验失败
            return false; // 拦截
        }
        // return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove(); // 清空ThreadLocal中的数据 防止内存泄漏
    }
}
