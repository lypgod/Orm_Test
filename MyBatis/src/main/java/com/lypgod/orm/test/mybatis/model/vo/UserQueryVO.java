package com.lypgod.orm.test.mybatis.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserQueryVO {
    private Integer id;
    private String username;
    private LocalDateTime birthDateBegin;
    private LocalDateTime birthDateEnd;
}
