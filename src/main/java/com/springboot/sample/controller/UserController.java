package com.springboot.sample.controller;

import com.springboot.sample.entity.User;
import com.springboot.sample.service.*;
import com.springboot.sample.service.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Proxy;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 利用thymeleaf模板引擎加载main主页
     * @return
     */
    @RequestMapping("/user/login")
    public String login() {
        return "main";
    }

    /**
     * 利用jdbc获取所有用户
     * @return
     */
    @RequestMapping("/user/getUsers")
    @ResponseBody
    public List<User> getUsers() {
        return userService.getUsers();
    }

    /**
     * 利用mybatis获取所有用户
     * @return
     */
    @RequestMapping("user/selectAllUsers")
    @ResponseBody
    public List<User> selectAllUsers() {
        return userService.selectAllUsers();
    }

    /**
     * 利用mybatis根据id获取用户
     * @param id
     * @return
     */
    @RequestMapping("/user/getUserById")
    @ResponseBody
    public User getUserById(int id) {
        return userService.getUserById(id);
    }

    /**
     * 利用mybatis pagehelper
     * @return
     */
    @RequestMapping("user/getUserPage")
    @ResponseBody
    public List<User> getUserPage() {
        return userService.getUserPage();
    }

    /**
     * 该方法利用JDK原生动态代理，在接口方法执行前先打印日志（打印接口方法名）
     */
    @RequestMapping("user/testProxy")
    @ResponseBody
    public String testProxy() {
        //通过Proxy创建接口对象
        UserInterface userInterface = (UserInterface) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class<?>[]{UserInterface.class},//要代理的接口方法
                new UserHandler(new UserImpl()));//代理的具体处理在UserHandler中定义
        userInterface.testProxy1();
        userInterface.testProxy2();
        return "success";
    }

    /**
     * 该方法利用cglib动态代理，在父类方法执行前先打印日志（打印调用方法名）
     * @return
     */
    @RequestMapping("user/testCglib")
    @ResponseBody
    public String testCglib(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserBase.class);//被代理类
        enhancer.setCallback(new UserInterceptor());//代理逻辑处理类
        //通过Enhancer创建父类对象（被代理类）
        UserBase base = (UserBase)enhancer.create();
        base.testCglib1();
        base.testCglib2();
        return "success";
    }
}
