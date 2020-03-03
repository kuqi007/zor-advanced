package com.zor.basic.MultiThread;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

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
