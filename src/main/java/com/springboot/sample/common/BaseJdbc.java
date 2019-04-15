package com.springboot.sample.common;

import com.springboot.sample.entity.User;
import com.springboot.sample.util.MOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BaseJdbc {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 返回对应类对象列表
     * 一次查询所有数据，适用于数据量不大，后台业务系统列表展示
     * @param sql
     * @param beanClass
     * @return
     */
    public List queryForList(String sql,Class beanClass) throws InstantiationException, IllegalAccessException {
        List<Map<String,Object>> mapList = jdbcTemplate.queryForList(sql);
        List list = new LinkedList();
        for(Map<String,Object> map:mapList){
            list.add(MOUtil.MapToObject(map, User.class));
        }
        return list;
    }

}
