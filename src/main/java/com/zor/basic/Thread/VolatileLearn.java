package com.zor.basic.Thread;

/**
 * Created by zqq on 2019/5/27.
 */
public class VolatileLearn {


    public static void main(String[] args) {
        NumberRange num = new NumberRange();
        num.setLower(0);
        num.setUpper(5);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                num.setLower(4);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                num.setUpper(3);//num在此处已经是final的
            }
        }).start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("(" + num.getLower() + "," + num.getUpper() + ")");
    }


}
