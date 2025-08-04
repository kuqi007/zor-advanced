package com.zor.basic.multithread;

import java.util.concurrent.Callable;

public class CallableDemo {
    public static void main(String[] args) {
        Callable<Object> callable = new Callable<Object>() {
            @Override
            public Object call() throws Exception {

                return null;
            }
        };

    }
}
