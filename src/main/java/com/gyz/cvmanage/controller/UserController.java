package com.gyz.cvmanage.controller;

import com.gyz.cvmanage.pojo.Result;
import com.gyz.cvmanage.pojo.User;
import com.gyz.cvmanage.service.UserService;
import com.gyz.cvmanage.util.JwtUtil;
import com.gyz.cvmanage.util.Md5Util;
import com.gyz.cvmanage.util.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User u = userService.findUserByName(username);
        // 判断用户是否存在
        if (u == null) {
            userService.register(username, password);
            return Result.success("注册成功");
        } else {
            return Result.error("当前用户名已存在！请重新注册");
        }
    }


    @PostMapping("/login")
    public Result login(String username, String password) {
        User loginUser = userService.findUserByName(username);
        if (loginUser == null) {
            return Result.error("用户名或密码错误！");
        }
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            // 存储到ThreadLocal中的业务数据 这里跟需要来即可
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        return Result.error("用户名或密码错误！");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(/* @RequestHeader(name = "Authorization") String token */) {
        // 根据用户名查询用户
        // Map<String, Object> map = JwtUtil.parseToken(token);
        // String username = (String) map.get("username");
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");

        User user = userService.findUserByName(username);
        return Result.success(user);
    }

}
