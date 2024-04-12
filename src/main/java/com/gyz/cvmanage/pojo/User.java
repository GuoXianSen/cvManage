package com.gyz.cvmanage.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 引入lombok依赖
 */
@Data
public class User {
    private Integer id;// 主键ID
    private String username;// 用户名
    @JsonIgnore
    private String password;// 密码
    private String nickname;// 昵称
    private String email;// 邮箱
    private String userPic;// 用户头像地址
    private LocalDateTime createTime;// 创建时间
    private LocalDateTime updateTime;// 更新时间
}
