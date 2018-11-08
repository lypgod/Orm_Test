package com.lypgod.orm.test.mybatis.model.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private LocalDateTime birthDate;

    private List<Order> orders;
}
