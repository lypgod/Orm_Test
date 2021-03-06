package com.lypgod.orm.test.mybatis.model.entity;

import lombok.Data;

import javax.persistence.Id;

@Data
public class Order {
    @Id
    private Integer id;
    private String name;
    private Integer quantity;
    private User user;
}
