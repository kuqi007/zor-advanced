package com.zor.basic.emum;

/**
 * Created by zqq on 2019/7/28.
 */
public enum Season {
    SPRING(1), SUMMER(2), AUTUMN(3), WINTER(4);

    private int code;

    Season(int code) {
        this.code = code;
    }

    public int getCode() {

        return code;

    }
}
