package com.zor.advanced.middleware.mq.disruptor;

/**
 * @author zhuqiqi03
 * @date 2021/7/1
 */
public class LongEvent {
    private long value;
    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
