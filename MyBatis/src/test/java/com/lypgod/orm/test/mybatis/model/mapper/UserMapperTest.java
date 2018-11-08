package com.lypgod.orm.test.mybatis.model.mapper;

import com.lypgod.orm.test.mybatis.model.entity.User;
import com.lypgod.orm.test.mybatis.model.vo.UserQueryVO;
import org.apache.ibatis.session.SqlSession;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        newUser.setBirthDate(LocalDateTime.of(2004, 4, 4, 4, 4, 4));
        assertEquals(this.userMapper.insertUser(newUser), 1);
        assertEquals(newUser.getId().intValue(), 1004);
    }

    @Test
    @Transactional
    public void insertUsersTest() {
        User newUser1 = new User();
        newUser1.setUsername("newUser1");
        newUser1.setPassword("newPassword1");
        newUser1.setBirthDate(LocalDateTime.of(2004, 4, 4, 4, 4, 4));
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
        User user = new User();
        user.setId(1001);
        user.setUsername("User1001");
        user.setBirthDate(LocalDateTime.of(2004, 4, 4, 4, 4, 4));
        assertEquals(this.userMapper.updateUser(user), 1);
        User user1 = this.userMapper.findUserById(user.getId());
        assertEquals(user1.getUsername(), "User1001");
        assertEquals(user1.getBirthDate(), LocalDateTime.of(2004, 4, 4, 4, 4, 4));
    }

    @Test
    public void findAllUsersTest() {
        assertEquals(this.userMapper.findAllUsers().size(), 3);
    }

    @Test
    public void findUserByIdTest() {
        User user = this.userMapper.findUserById(1001);
        assertEquals(user.getUsername(), "User1");
        assertEquals(user.getBirthDate(), LocalDateTime.of(2001, 1, 1, 1, 1, 1));
    }

    @Test
    public void findindUserByNameLikeTest() {
        assertEquals(this.userMapper.findUserByNameLike("%2%").size(), 1);
        assertEquals(this.userMapper.findUserByNameLike("%User%").size(), 3);
    }

    @Test
    public void findindUserByConditionTest() {
        UserQueryVO queryUser = new UserQueryVO();
        queryUser.setUsername("2");
        assertEquals(this.userMapper.findUserByCondition(queryUser).size(), 1);

        queryUser = new UserQueryVO();
        queryUser.setId(1001);
        assertEquals(this.userMapper.findUserByCondition(queryUser).size(), 1);

        queryUser = new UserQueryVO();
        queryUser.setBirthDateBegin(LocalDateTime.of(2002, 1, 1, 0, 0, 0));
        assertEquals(this.userMapper.findUserByCondition(queryUser).size(), 2);

        queryUser = new UserQueryVO();
        queryUser.setBirthDateEnd(LocalDateTime.of(2003, 3, 3, 23, 59, 59, 999));
        assertEquals(this.userMapper.findUserByCondition(queryUser).size(), 3);

        queryUser = new UserQueryVO();
        queryUser.setBirthDateBegin(LocalDateTime.of(2002, 1, 1, 1, 1, 1, 0));
        queryUser.setBirthDateEnd(LocalDateTime.of(2003, 3, 3, 23, 59, 59, 999));
        assertEquals(this.userMapper.findUserByCondition(queryUser).size(), 2);
    }

    @Test
    @Transactional
    public void deleteBatchUserTest() {
        User user = new User();
        user.setUsername("newUser");
        user.setPassword("newPassword");
        this.userMapper.insertUser(user);

        assertEquals(this.userMapper.findAllUsers().size(), 4);
        assertEquals(this.userMapper.deleteBatchUser(Arrays.asList(1003, 1004)), 2);
        assertEquals(this.userMapper.findAllUsers().size(), 2);
    }

    @Test
    public void findUserWithOrdersByIdTest() {
        assertEquals(this.userMapper.findUserWithOrdersById(1001).getOrders().size(), 2);
    }


    @Ignore
    @Test
    public void generateUsersTest() {
        final int[] index = {4};

        this.userMapper.insertUsers(
                Stream.generate(() -> {
                            User user = new User();
                            user.setUsername("User" + index[0]);
                            user.setPassword("password" + index[0]);
                            user.setBirthDate(LocalDateTime.of(2001, 1, index[0], 0, 0, 0));
                            index[0]++;
                            return user;
                        }
                ).limit(20).collect(Collectors.toList()));

        this.userMapper.findAllUsers().forEach(System.out::println);
    }
}