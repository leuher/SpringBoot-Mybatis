package com.ray.mybatis6.service;

import com.ray.mybatis6.dao.CityMapper;
import com.ray.mybatis6.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ray
 * @date 2018/7/8 0008
 * 实现service
 */
@Service
public class CityService implements IService<City> {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public City selectByKey(Object key) {
        return cityMapper.selectByPrimaryKey(key);
    }

    @Override
    public List<City> selectAll() {
        return cityMapper.selectAll();
    }

    @Override
    public int save(City entity) {
        return cityMapper.insert(entity);
    }

    @Override
    public int delete(Object key) {
        return cityMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int update(City entity) {
        if(entity.getId() != null){
            return cityMapper.updateByPrimaryKey(entity);
        }
        return 0;
    }

}
