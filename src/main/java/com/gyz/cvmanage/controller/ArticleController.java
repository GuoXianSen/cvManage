package com.gyz.cvmanage.controller;

import com.gyz.cvmanage.pojo.Result;
import com.gyz.cvmanage.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list(/*@RequestHeader(name = "Authorization") String token, HttpServletResponse response*/) {
        // 验证token
        // try {
        //     Map<String, Object> claims = JwtUtil.parseToken(token);
        //     return Result.success("所有文章数据");
        // } catch (Exception e) {
        //     // throw new RuntimeException(e);
        //     response.setStatus(401);  // 未授权 校验失败
        //     return Result.error("未登录.....");
        // }
        // TODO 文章列表接口
        return Result.success("所有文章数据");
    }
}
