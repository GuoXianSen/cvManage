package com.gyz.cvmanage.controller;

import com.gyz.cvmanage.pojo.Result;
import com.gyz.cvmanage.pojo.User;
import com.gyz.cvmanage.service.UserService;
import com.gyz.cvmanage.util.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        if (Md5Util.getMD5String(loginUser.getPassword()).equals(password)) {
            return Result.success("登录成功JWT");
        }

        return Result.error("用户名或密码错误！");
    }

}
