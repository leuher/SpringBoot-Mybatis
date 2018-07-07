package com.ray.mybatis.mapper;

import com.ray.mybatis.entity.Combo;

/**
 * @author Ray
 * @date 2018/7/7 0007
 * 数据操作层
 */
public interface ComboMapper {

    /**
     * 根据id查询运营商拥有的用户
     */
    public Combo selectUserById(Integer id);
}
