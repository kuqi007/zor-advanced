package com.zor.basic.highconcurrency.concurrent;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampReferenceDemo {

    static AtomicStampedReference<Integer> money = new AtomicStampedReference<>(19, 0);

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {

            int stamp = money.getStamp();

            System.out.println("stamp的值是" + stamp);

            //充值线程
            new Thread(() -> {

                while (true) {

                    Integer account = money.getReference();

                    if (account < 20) {

                        if (money.compareAndSet(account, account + 20, stamp, stamp + 1)) {

                            System.out.println("余额小于20元，充值成功，目前余额：" + money.getReference() + "元");
                            break;
                        }
                    } else {

                        System.out.println("余额大于20元,无需充值");
                    }
                }
            }).start();
        }

        new Thread(() -> {    //消费线程

            for (int j = 0; j < 100; j++) {

                while (true) {

                    int timeStamp = money.getStamp();//1

                    int currentMoney = money.getReference();//39

                    if (currentMoney > 10) {
                        System.out.println("当前账户余额大于10元");
                        if (money.compareAndSet(currentMoney, currentMoney - 10, timeStamp, timeStamp + 1)) {

                            System.out.println("消费者成功消费10元，余额" + money.getReference());

                            break;
                        }
                    } else {
                        System.out.println("没有足够的金额");
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        break;
                    }
                }
            }
        }).start();

    }
}
