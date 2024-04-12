package com.gyz.cvmanage.service.impl;

import com.gyz.cvmanage.mapper.UserMapper;
import com.gyz.cvmanage.pojo.User;
import com.gyz.cvmanage.service.UserService;
import com.gyz.cvmanage.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public void register(String username, String password) {
        String md5Pwd = Md5Util.getMD5String(password);
        userMapper.add(username,md5Pwd);
    }
}
