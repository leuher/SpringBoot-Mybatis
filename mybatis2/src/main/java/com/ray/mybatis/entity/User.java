package com.ray.mybatis.entity;

/**
 * @author Ray
 * @date 2018/7/7 0007
 * 用户实体类
 */
public class User {

    /**
     * 编号（主键）
     */
    private int id;

    /**
     * 用户名
     */
    private String name;

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

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
