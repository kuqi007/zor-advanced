package com.zor.advanced.ddd.meituan.lottery.context;

/**
 * Created by kuqi0 on 2021/3/7
 */
public class LotteryContext {
    private Object lat;
    private Object lng;
    private int lotteryId;
    private Object userId;

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public Object getLng() {
        return lng;
    }

    public void setLng(Object lng) {
        this.lng = lng;
    }

    public int getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(int lotteryId) {
        this.lotteryId = lotteryId;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }
}
