package com.lypgod.orm.test.mybatis.model.mapper;

import com.lypgod.orm.test.mybatis.model.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;
    @Resource
    private SqlSession sqlSession;

    @Test
    @Transactional
    public void insertUserTest() {
        User newUser = new User();
        newUser.setUsername("newUser");
        newUser.setPassword("newPassword");
        assertEquals(this.userMapper.insertUser(newUser), 1);
        assertEquals(newUser.getId(), 1004);
    }

    @Test
    @Transactional
    public void insertUsersTest() {
        User newUser1 = new User();
        newUser1.setUsername("newUser1");
        newUser1.setPassword("newPassword1");
        User newUser2 = new User();
        newUser2.setUsername("newUser2");
        newUser2.setPassword("newPassword2");
        List<User> newUsers = Arrays.asList(newUser1, newUser2);
        assertEquals(this.userMapper.insertUsers(newUsers), 2);
    }

    @Test
    @Transactional
    public void deleteUserTest() {
        assertEquals(this.userMapper.findAllUsers().size(), 3);
        assertEquals(this.userMapper.deleteUser(1003), 1);
        assertEquals(this.userMapper.findAllUsers().size(), 2);
    }

    @Test
    @Transactional
    public void updateUserTest() {
        User user = this.userMapper.findUserById(1001);
        user.setUsername("User1001");
        assertEquals(this.userMapper.updateUser(user), 1);
        assertEquals(this.userMapper.findUserById(1001).getUsername(), "User1001");

    }

    @Test
    public void findAllUsersTest() {
        assertEquals(this.userMapper.findAllUsers().size(), 3);
    }

    @Test
    public void findUserByIdTest() {
        assertEquals(this.userMapper.findUserById(1001).getUsername(), "User1");
        assertEquals(((User) this.sqlSession.selectOne("com.lypgod.orm.test.mybatis.model.mapper.UserMapper.findUserById", 1002)).getUsername(), "User2");
    }
}