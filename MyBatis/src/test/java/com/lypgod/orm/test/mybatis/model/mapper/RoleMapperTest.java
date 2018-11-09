package com.lypgod.orm.test.mybatis.model.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class RoleMapperTest {

    @Resource
    private RoleMapper roleMapper;

    @Test
    public void findRoleWithUsersByIdTest() {
        assertEquals(this.roleMapper.findRoleWithUsersById(1).getUsers().size(), 2);
    }
}