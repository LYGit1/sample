package com.springboot.sample.controller;

import com.springboot.sample.entity.User;
import com.springboot.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * rest接口
 */
@RestController
public class RestUserController {

    @Autowired
    private UserService userService;

    /**
     * 新增一个用户
     * @param user
     * @return
     */
    @PostMapping("user/saveUser")
    public User saveUser(User user){
        return userService.saveUser(user);
    }

    /**
     * 根据条件查询用户列表
     * @param user
     * @return
     */
    @PostMapping("user/getUsersByCondition")
    public List<User> getUsersByCondition(User user){
        return userService.selectByCondition(user);
    }
}
