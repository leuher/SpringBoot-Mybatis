package com.ray.mybatis.controller;

import com.ray.mybatis.entity.User;
import com.ray.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ray
 * @date 2018/7/7 0007
 * 数据控制层
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据id获取用户信息
     * localhost:8080/user/2
     */
    @GetMapping("/user/{id}")
    public User selectById(@PathVariable("id")Integer id){
        User user = userMapper.selectById(id);
        return user;
    }

    /**
     * 查询所有用户信息
     * localhost:8080/users
     */
    @GetMapping("/users")
    public List<User> list(){
        List<User> users = userMapper.list();
        return users;
    }

    /**
     * 新增用户信息
     * localhost:8080/user?name=Ray1
     */
    @PostMapping("/user")
    public String insert(User user){
        int result = userMapper.insert(user);
        if(result == 1){
            return "success";
        }
        return "fail";
    }

    /**
     * 修改用户信息
     * localhost:8080/user/2?name=Ray22
     */
    @PutMapping("/user/{id}")
    public String update(User user, @PathVariable("id") Integer id){
        int result = userMapper.update(user);
        if(result == 1){
            return "success";
        }
        return "fail";
    }

    /**
     * 删除用户信息
     * localhost:8080/user/8
     */
    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id") Integer id){
        int result = userMapper.delete(id);
        if(result == 1){
            return "success";
        }
        return "fail";
    }
}
