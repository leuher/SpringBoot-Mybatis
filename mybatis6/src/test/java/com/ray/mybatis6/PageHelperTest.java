package com.ray.mybatis6;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ray.mybatis6.dao.CityMapper;
import com.ray.mybatis6.entity.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Ray
 * @date 2018/7/8 0008
 * 分页信息说明
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PageHelperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageHelperTest.class);

    @Autowired
    private CityMapper cityMapper;

    @Test
    public void test(){
        PageHelper.startPage(1,5); // 分页核心代码 (页，行)
        PageInfo<City> cityPageInfo = new PageInfo<>(this.cityMapper.selectAll());
        LOGGER.info("[分页信息] - [{}]", cityPageInfo);
        LOGGER.info("[当前页号-PageNum] - [{}]", cityPageInfo.getPageNum());
        LOGGER.info("[页面大小-PageSize] - [{}]", cityPageInfo.getPageSize());
        LOGGER.info("[起始行号-StartRow] - [{}]", cityPageInfo.getStartRow());
        LOGGER.info("[终止行号-EndRow] - [{}]", cityPageInfo.getEndRow());
        LOGGER.info("[总结果数-Total] - [{}]", cityPageInfo.getTotal());
        LOGGER.info("[总页数-Pages] - [{}]", cityPageInfo.getPages());
        LOGGER.info("[分页列表-List] - [{}]", cityPageInfo.getList());
    }
}
