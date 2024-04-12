package com.gyz.cvmanage.service;

import com.gyz.cvmanage.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    // 根据用户名查找用户
    User findUserByName(String username);

    // 注册
    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatarUrl);
}
