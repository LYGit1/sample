package com.springboot.sample.common;

import com.springboot.sample.util.MapObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
            list.add(MapObjectUtil.mapToObject(map, beanClass));
        }
        return list;
    }

}
