package com.zor.basic.highconcurrency;

/**
 * Created by zqq on 2019/5/27.
 */
public class NumberRange {

    private int lower, upper;

    public int getLower() {
        return lower;
    }

    public int getUpper() {
        return upper;
    }

    public void setLower(int value) {
        if (value > upper) {
            throw new IllegalArgumentException(value + "  value > upper" + upper);
        }
        lower = value;
    }

    public void setUpper(int value) {
        if (value < lower) {
            throw new IllegalArgumentException(value + "  value < lower" + lower);
        }
        upper = value;
    }

}
