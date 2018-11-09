package com.lypgod.orm.test.mybatis.model.entity;

import lombok.Data;

import javax.persistence.Id;
import java.util.List;

@Data
public class Role {
    @Id
    private Integer id;
    private String name;

    private List<User> users;
}
