package com.ray.mybatis6.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ray
 * @date 2018/7/8 0008
 * 通用service接口
 */
@Service
public interface IService<T> {

    T selectByKey(Object key);

    List<T> selectAll();

    int save(T entity);

    int delete(Object key);

    int update(T entity);

    //TODO 其他...
}
