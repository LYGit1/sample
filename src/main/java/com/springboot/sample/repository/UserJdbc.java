package com.springboot.sample.repository;

import com.springboot.sample.common.BaseJdbc;
import com.springboot.sample.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbc extends BaseJdbc {

    /**
     * 获取所有用户列表
     * @return
     */
    public List<User> getUsers(){
        String sql = "select * from user";
        List<User> list = null;
        try {
            list = queryForList(sql, User.class);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

}
