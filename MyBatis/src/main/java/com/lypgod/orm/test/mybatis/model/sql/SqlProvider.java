package com.lypgod.orm.test.mybatis.model.sql;

import org.apache.ibatis.jdbc.SQL;

public class SqlProvider {

    public String sqlFindUserByNameLike() {
        return new SQL()
                .SELECT("id", "username", "password")
                .FROM("User")
                .WHERE("username like #{?}")
                .toString();
    }
}
