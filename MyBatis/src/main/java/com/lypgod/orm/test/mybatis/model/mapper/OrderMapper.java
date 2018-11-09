package com.lypgod.orm.test.mybatis.model.mapper;

import com.lypgod.orm.test.mybatis.model.entity.Order;
import com.lypgod.orm.test.mybatis.model.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface OrderMapper {

    @Select("SELECT * FROM `ORDER` WHERE id = #{id}")
    @Results({
            @Result(property = "user", column = "user_id", one = @One(select = "com.lypgod.orm.test.mybatis.model.mapper.UserMapper.findUserById"))
    })
    Order findOrderById(int id);

    @Select("SELECT * FROM `ORDER` WHERE user_id = #{id}")
    List<Order> findOrdersByUser(User user);

}