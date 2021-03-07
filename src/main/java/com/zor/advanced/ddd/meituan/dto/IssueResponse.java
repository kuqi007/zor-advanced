package com.zor.advanced.ddd.meituan.dto;

/**
 * Created by kuqi0 on 2021/3/7
 */
public class IssueResponse {
    public static final int OK = 200;

    private Integer code;

    public Integer getCode() {

        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public PrizeInfo getPrizeInfo() {
        return null;
    }
}
