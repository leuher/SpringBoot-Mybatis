package com.ray.mybatis.controller;

import com.ray.mybatis.entity.IDCard;
import com.ray.mybatis.mapper.IDCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ray
 * @date 2018/7/7 0007
 * 数据控制层
 */
@RestController
public class IDCardController {

    @Autowired
    private IDCardMapper idCardMapper;

    /**
     *  获取用户信息的身份证列表
     *  localhost:8080/selectIdOfUser
     */
    @GetMapping("/selectIdOfUser")
    public List<IDCard> listOfUser(){
        List<IDCard> idCards = idCardMapper.selectIdOfUser();
        return idCards;
    }
}
