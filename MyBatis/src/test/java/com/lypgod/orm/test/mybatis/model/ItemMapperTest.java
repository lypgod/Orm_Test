package com.lypgod.orm.test.mybatis.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ItemMapperTest {
    @Autowired
    private ItemMapper itemMapper;

    @Test
    public void testFindAllItems() {
        System.out.println(this.itemMapper.findAllItems());
    }

    @Test
    public void testFindItemById() {
        System.out.println(this.itemMapper.getItemById(10001));
    }
}