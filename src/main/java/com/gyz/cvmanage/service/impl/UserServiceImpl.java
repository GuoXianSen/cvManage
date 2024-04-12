package com.gyz.cvmanage.service.impl;

import com.gyz.cvmanage.mapper.UserMapper;
import com.gyz.cvmanage.pojo.User;
import com.gyz.cvmanage.service.UserService;
import com.gyz.cvmanage.util.Md5Util;
import com.gyz.cvmanage.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

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

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now()); // 设置更新的时间为当前时间
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        String md5Pwd = Md5Util.getMD5String(newPwd);
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(md5Pwd,id);
    }

}
