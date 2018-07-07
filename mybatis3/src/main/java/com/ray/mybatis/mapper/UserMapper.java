package com.ray.mybatis.mapper;

import com.ray.mybatis.entity.User;

import java.util.List;

/**
 * @author Ray
 * @date 2018/7/7 0007
 * 数据操作层
 */
public interface UserMapper {

    /**
     * 根据id获取用户信息
     */
    public User selectById(Integer id);

    /**
     * 查询所有用户信息
     */
    public List<User> list();

    /**
     * 新增用户信息
     */
    public int insert(User user);

    /**
     * 修改用户信息
     */
    public int update(User user);

    /**
     * 删除用户信息
     */
    public int delete(Integer id);

    /**
     * 根据id查询用户的所有电话信息
     */
    public User selectPhoneById(Integer id);
}
