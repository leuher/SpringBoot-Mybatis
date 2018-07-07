package com.ray.mybatis.entity;

import java.util.List;

/**
 * @author Ray
 * @date 2018/7/7 0007
 * 运营商实体类
 */
public class Combo {

    private int id;
    private String name;

    private List<UserCombo> userCombos;

    public List<UserCombo> getUserCombos() {
        return userCombos;
    }

    public void setUserCombos(List<UserCombo> userCombos) {
        this.userCombos = userCombos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Combo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
