package com.ray.mybatis.entity;

/**
 * @author Ray
 * @date 2018/7/7 0007
 * 手机实体
 */
public class Phone {

    /**
     * 编号
     */
    private int id;

    /**
     * 手机名称
     */
    private String name;

    /**
     * 手机号码
     */
    private long number;

    /**
     * 用户编号
     */
    private int uid;

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

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", uid=" + uid +
                '}';
    }
}
