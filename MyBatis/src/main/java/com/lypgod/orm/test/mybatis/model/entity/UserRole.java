package com.lypgod.orm.test.mybatis.model.entity;

import lombok.Data;

@Data
public class UserRole {
    private User user;
    private Role role;
}
