package com.springboot.sample.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.springboot.sample.entity.User;
import com.springboot.sample.exception.DaoRuntimeException;
import com.springboot.sample.mapper.UserMapper;
import com.springboot.sample.repository.UserJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserJdbc userJdbc;
    @Autowired
    private UserMapper userMapper;

    /**
     * 通过jdbc查询所有用户
     * @return
     */
    public List<User> getUsers(){
        List<User> list = new ArrayList<>();
        try{
            list = userJdbc.getUsers();
        }catch (Exception e){
            throw new DaoRuntimeException(e.getLocalizedMessage());
        }
        return list;
    }

    /**
     * 通过mybatis查询所有用户
     * @return
     */
    public List<User> selectAllUsers(){
        return userMapper.selectAllUsers();
    }

    /**
     * 通过mybatis查询用户
     * @return
     */
    public User getUserById(int id){
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * @Description mybatis pagehelper分页查询
     * @return
     */
    public List<User> getUserPage(){
        Page<User> page = PageHelper.startPage(1,1,true);
        List<User> list = userMapper.selectAllUsers();
        page.getTotal();
        return list;
    }

    /**
     * @Description 新增一个用户
     * @param user
     * @return
     */
    public User saveUser(User user){
        int i = userMapper.insert(user);
        if(1 != i){
            throw new DaoRuntimeException("新增用户失败！");
        }
        return user;
    }

    /**
     * 根据条件查询用户列表
     * @param user
     * @return
     */
    public List<User> selectByCondition(User user){
        return userMapper.selectByCondition(user);
    }

}
