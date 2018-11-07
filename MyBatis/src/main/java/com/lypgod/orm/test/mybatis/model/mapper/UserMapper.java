package com.lypgod.orm.test.mybatis.model.mapper;

import com.lypgod.orm.test.mybatis.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
}