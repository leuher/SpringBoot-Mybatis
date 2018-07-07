package com.ray.mybatis.mapper;

import com.ray.mybatis.entity.IDCard;

import java.util.List;

/**
 * @author Ray
 * @date 2018/7/7 0007
 * 数据操作层
 */
public interface IDCardMapper {
    /**
     * 获取用户信息的身份证列表
     */
    public List<IDCard> selectIdOfUser();
}
