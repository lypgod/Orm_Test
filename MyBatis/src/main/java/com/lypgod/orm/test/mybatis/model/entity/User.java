package com.lypgod.orm.test.mybatis.model.entity;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class User extends PageInfo<User> {
    @Id
    private Integer id;
    private String username;
    private String password;
    private LocalDateTime birthDate;

    private List<Order> orders;

    private List<Role> roles;
}
