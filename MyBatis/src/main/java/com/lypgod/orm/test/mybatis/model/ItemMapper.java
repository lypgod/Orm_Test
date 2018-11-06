package com.lypgod.orm.test.mybatis.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author richard
 */
@Mapper
@Component
public interface ItemMapper {
    /**
     * findAllItems
     *
     * @return List<Item>
     */
    @Select("SELECT * FROM ITEM")
    List<Item> findAllItems();

    Item getItemById(int id);
}