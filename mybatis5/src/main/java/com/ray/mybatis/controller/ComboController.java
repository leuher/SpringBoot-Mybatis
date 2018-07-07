package com.ray.mybatis.controller;

import com.ray.mybatis.entity.Combo;
import com.ray.mybatis.mapper.ComboMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ray
 * @date 2018/7/7 0007
 * 数据控制层
 */
@RestController
public class ComboController {

    @Autowired
    private ComboMapper comboMapper;

    /**
     * 根据id查询运营商拥有的用户和电话信息
     * localhost:8080/combo/user/2
     */
    @GetMapping("/combo/user/{id}")
    public Combo selectUserById(@PathVariable("id") Integer id){
        Combo combo = comboMapper.selectUserById(id);
        return combo;
    }
}
