package com.lypgod.orm.test.mybatis.model.mapper;

import com.lypgod.orm.test.mybatis.model.entity.User;
import com.lypgod.orm.test.mybatis.model.sql.SqlProvider;
import com.lypgod.orm.test.mybatis.model.vo.UserQueryVO;
import org.apache.ibatis.annotations.*;
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

    List<User> findUserByCondition(UserQueryVO queryUser);

    int deleteBatchUser(List<Integer> ids);

    @Select("SELECT * FROM User WHERE id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "orders", column = "id", many = @Many(select = "com.lypgod.orm.test.mybatis.model.mapper.OrderMapper.findOrdersByUser"))
    })
    User findUserWithOrdersById(int id);
}