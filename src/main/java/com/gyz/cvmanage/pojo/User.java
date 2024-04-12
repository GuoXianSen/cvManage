package com.gyz.cvmanage.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 引入lombok依赖
 */
@Data
public class User {
    @NotNull // 值不为null
    private Integer id;// 主键ID
    private String username;// 用户名
    @JsonIgnore
    private String password;// 密码
    @NotEmpty // 不能为null并且内容不为空
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;// 昵称
    @NotEmpty
    @Email // 满足邮箱格式
    private String email;// 邮箱
    private String userPic;// 用户头像地址
    private LocalDateTime createTime;// 创建时间
    private LocalDateTime updateTime;// 更新时间
}
