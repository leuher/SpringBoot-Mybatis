package com.ray.mybatis6.controller;

import com.github.pagehelper.PageHelper;
import com.ray.mybatis6.entity.City;
import com.ray.mybatis6.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ray
 * @date 2018/7/8 0008
 * 数据控制层
 */
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     * 查询所有城市信息
     * localhost:8080/cities
     */
    @GetMapping("/cities")
    public List<City> selectAll(){
        List<City> cities = cityService.selectAll();
        return cities;
    }

    /**
     * 查询所有城市信息[分页查询]
     * 动态改变页数，固定每页行数
     * localhost:8080/cities/3
     */
    @GetMapping("/cities/{page}")
    public List<City> selectPage(@PathVariable("page") int page){
        PageHelper.startPage(page,5);
        List<City> cities = cityService.selectAll();
        return cities;
    }

    /**
     * 根据id查询城市信息
     * localhost:8080/city/1
     */
    @GetMapping("/city/{id}")
    public City selectByKey(@PathVariable("id") Integer id){
        City city = cityService.selectByKey(id);
        return city;
    }

    /**
     * 新增城市信息
     * localhost:8080/city?name=深圳&state=广东
     */
    @PostMapping("/city")
    public String save(City city){
        int result = cityService.save(city);
        if(result == 1){
            return "success";
        }
        return "fail";
    }

    /**
     * 修改城市信息
     * localhost:8080/city/1?name=云浮&state=广东
     */
    @PutMapping("/city/{id}")
    public String update(City city, @PathVariable("id") Integer id){
        int result = cityService.update(city);
        if(result == 1){
            return "success";
        }
        return "fail";
    }

    /**
     * 删除城市信息
     * localhost:8080/city/5
     */
    @DeleteMapping("/city/{id}")
    public String delete(@PathVariable("id") Integer id){
        int result = cityService.delete(id);
        if(result == 1){
            return "success";
        }
        return "fail";
    }
}
