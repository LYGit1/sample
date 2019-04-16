package com.springboot.sample.mapper;

import com.springboot.sample.entity.User;

import java.util.List;

public interface UserMapper {

    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 查询所有用户对象列表
     * @return
     */
    List<User> selectAllUsers();

    /**
     * 根据条件查询用户列表
     * @param user
     * @return
     */
    List<User> selectByCondition(User user);
}