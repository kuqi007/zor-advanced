package com.zor.interview.online;

import java.util.HashMap;
import java.util.Map;

/**
 * applovin一面
 * 接单系统，有美团外卖、饿了么外卖，如何设计一个优雅的系统
 * Created by kuqi0 on 2022/5/26
 */
public class ReceiveOrder {

    ReceiverOrderFactory factory;

    public void receiverOrder(String platformType, Object... params) {

        ReceiverOrder processor = factory.getReceiverOrderProcessor(platformType);
        processor.receiverOrder(params);

    }
}


interface ReceiverOrder {

    void receiverOrder(Object... params);

    String platformType();

}


class ReceiverOrderFactory {

    private final Map<String, ReceiverOrder> map = new HashMap<>();

    // List<ReceiverOrder> receiverOrders;

    // static{
    //   Reveiver meituanProcessor = SingeInstance.getInstance();
    //   Reveiver meituanProcessor = SingeInstance.getInstance();
    //   map.put("mt",meituanProcessor);
    // }

    // public init(){
    //   // 按照平台和实现放到map里面

    // }


    protected void set(String platformType, ReceiverOrder receiverOrder) {
        map.put(platformType, receiverOrder);
    }


    public ReceiverOrder getReceiverOrderProcessor(String platformType) {
        return map.get(platformType);
    }

}

abstract class AbstractReceiverOrderProcessor extends ReceiverOrderFactory implements ReceiverOrder {


    public void receiverOrder(Object... params) {

        doBefore(params);

        doReceiverOrder(params);

        doAfter(params);

    }

    public abstract void doReceiverOrder(Object... params);

    public abstract void doBefore(Object... params);

    public abstract void doAfter(Object... params);


    public abstract void set();


}


class MeituanReceiverOrder extends AbstractReceiverOrderProcessor {

    private static MeituanReceiverOrder meituanProcessor = null;

    public void set() {
        super.set(platformType(), getInstance());
    }

    public void doReceiverOrder(Object... params) {

    }

    public void doBefore(Object... params) {

    }

    public void doAfter(Object... params) {

    }

    public static ReceiverOrder getInstance() {
        if (meituanProcessor == null) {
            meituanProcessor = new MeituanReceiverOrder();
        }

        return meituanProcessor;
    }

    @Override
    public String platformType() {
        return "meituan";
    }
}


class ElemeReceiverOrder extends AbstractReceiverOrderProcessor {

    private static ElemeReceiverOrder elemeReceiverOrder = null;

    public void doReceiverOrder(Object... params) {

    }

    public void doBefore(Object... params) {

    }

    public void doAfter(Object... params) {

    }

    @Override
    public void set() {
        super.set(platformType(), getInstance());
    }

    public static ReceiverOrder getInstance() {
        if (elemeReceiverOrder == null) {
            elemeReceiverOrder = new ElemeReceiverOrder();
        }

        return elemeReceiverOrder;
    }

    @Override
    public String platformType() {
        return "eleme";
    }
}