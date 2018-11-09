package com.lypgod.orm.test.mybatis.model.mapper;

import com.lypgod.orm.test.mybatis.model.entity.User;
import com.lypgod.orm.test.mybatis.model.sql.SqlProvider;
import com.lypgod.orm.test.mybatis.model.vo.UserQueryVO;
import com.lypgod.orm.test.mybatis.util.MyMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper extends MyMapper<User> {

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


    @Select("SELECT * FROM User WHERE id in (SELECT user_id FROM USER_ROLE WHERE role_id = #{roleId})")
    List<User> findUsersByRoleId(int roleId);


    @Select("SELECT * FROM User")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "id", property = "roles",
                    many = @Many(
                            select = "com.lypgod.orm.test.mybatis.model.mapper.RoleMapper.findRolesByUserId",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    List<User> findAllUsersWithRoles();

    default void save(User user) {
        if (user.getId() != null) {
            this.updateByPrimaryKey(user);
        } else {
            this.insert(user);
        }
    }
}