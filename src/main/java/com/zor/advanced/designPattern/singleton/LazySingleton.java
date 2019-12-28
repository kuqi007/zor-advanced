package com.zor.advanced.designPattern.singleton;


/**
 * 双重检测机制(DCL)懒汉式
 * 参考
 * https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484239&idx=1&sn=6560be96e456b513cb1e4f78a740a258&chksm=ebd7424edca0cb584906fb97679cf2ca557f430fbc87d2c86ce0652d2e3c36c2528466942df5&scene=21###wechat_redirect
 */
public class LazySingleton {
    private volatile static LazySingleton lazySingleton = null;

    private LazySingleton() {
    }

    private static LazySingleton getInstance() {
        if (lazySingleton == null) {//判空为了提升性能
            synchronized (LazySingleton.class) {
                if (lazySingleton == null) {//进入区域后，再检查一次，如果仍是null,才创建实例
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }

}
