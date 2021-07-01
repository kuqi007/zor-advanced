package com.zor.advanced.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author zhuqiqi03
 * @date 2021/7/1
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
