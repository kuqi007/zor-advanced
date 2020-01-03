package com.zor.basic.basicType;

import org.junit.Test;

/**
 * Created by zqq on 2020/1/3.
 */
public class BasicTypeTest {

    /**
     * a+=b  会出现负数。-2
     * <p>
     * a=a+b 会直接报错。
     * <p>
     * 强制类型提升造成的
     */
    @Test
    public void byteTest() {
        byte a = 127;
        byte b = 127;
        a += b;
        //a = a + b;
        System.out.println(a);
    }

    /**
     * 下面代码，最后会输出-56。原因如下：
     * <p>
     * 456的二进制表示是111001000，由于int是32位的二进制，所以在计算机中，实际上是00000000000……111001000，当int转成byte的时候，那么计算机会只保留最后8位，即11001000。
     * <p>
     * 然后11001000的最高位是1，那么表示是一个负数，而负数在计算机中都是以补码的形式保存的，所以我们计算11001000的原码为00111000，即56，所以11001000表示的是-56，所以最后test的值为-56。
     */
    @Test
    public void byteTest2() {
        int b = 456;
        byte test = (byte) b;
        System.out.println(test);
    }
}
