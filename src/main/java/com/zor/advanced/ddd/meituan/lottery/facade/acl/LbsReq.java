package com.zor.advanced.ddd.meituan.lottery.facade.acl;

/**
 * Created by kuqi0 on 2021/3/7
 */
public class LbsReq {
    private Object lat;
    private Object lng;

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public Object getLat() {
        return lat;
    }

    public void setLng(Object lng) {
        this.lng = lng;
    }

    public Object getLng() {
        return lng;
    }
}
