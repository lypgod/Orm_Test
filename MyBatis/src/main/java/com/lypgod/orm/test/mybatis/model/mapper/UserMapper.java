package com.lypgod.orm.test.mybatis.model.mapper;

import com.lypgod.orm.test.mybatis.model.entity.User;
import com.lypgod.orm.test.mybatis.model.sql.SqlProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    int insertUser(User user);

    int insertUsers(List<User> user);

    int deleteUser(int id);

    int updateUser(User user);

    User findUserById(int id);

    @Select("SELECT * FROM USER")
    List<User> findAllUsers();

    @SelectProvider(type = SqlProvider.class, method = "sqlFindUserByNameLike")
    List<User> findUserByNameLike(String str1);
}