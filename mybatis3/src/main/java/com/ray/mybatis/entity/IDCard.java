package com.ray.mybatis.entity;

/**
 * @author Ray
 * @date 2018/7/7 0007
 * 身份证实体
 */
public class IDCard {

    /**
     * 用户编号
     */
    private int uid;

    /**
     * 身份证
     */
    private long cardId;

    /**
     * 用户实体类
     */
    private User user;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
