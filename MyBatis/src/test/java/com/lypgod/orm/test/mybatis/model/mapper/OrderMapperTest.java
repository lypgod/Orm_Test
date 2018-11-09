package com.lypgod.orm.test.mybatis.model.mapper;

import com.lypgod.orm.test.mybatis.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class OrderMapperTest {
    @Resource
    private OrderMapper orderMapper;

    @Test
    public void findOrderByIdTest() {
        assertEquals(this.orderMapper.findOrderById(1).getUser().getUsername(), "User1");
    }

    @Test
    public void findOrdersByUserTest() {
        User user = new User();
        user.setId(1001);
        assertEquals(this.orderMapper.findOrdersByUser(user).size(), 2);
    }
}