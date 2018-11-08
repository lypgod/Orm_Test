package com.lypgod.orm.test.mybatis.model.entity;

import lombok.Data;

@Data
public class Order {
    private Integer id;
    private String name;
    private Integer quantity;
    private User user;
}
