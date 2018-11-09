package com.lypgod.orm.test.mybatis.model.mapper;

import com.lypgod.orm.test.mybatis.model.entity.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RoleMapper {

    @Select("SELECT * FROM Role WHERE id=#{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "id", property = "users",
                    many = @Many(
                            select = "com.lypgod.orm.test.mybatis.model.mapper.UserMapper.findUsersByRoleId",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    Role findRoleWithUsersById(int id);

    @Select("SELECT * FROM Role WHERE id in (SELECT role_id FROM USER_ROLE WHERE user_id = #{userId})")
    List<Role> findRolesByUserId(int userId);
}