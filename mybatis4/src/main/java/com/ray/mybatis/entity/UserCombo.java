package com.ray.mybatis.entity;

import java.util.List;

/**
 * @author Ray
 * @date 2018/7/7 0007
 * 用户拥有运营商实体
 */
public class UserCombo {

    private int uid;
    private int cid;
    private int price;

    private List<Combo> combos;

    public List<Combo> getCombos() {
        return combos;
    }

    public void setCombos(List<Combo> combos) {
        this.combos = combos;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "UserCombo{" +
                "uid=" + uid +
                ", cid=" + cid +
                ", price=" + price +
                ", combos=" + combos +
                '}';
    }
}
