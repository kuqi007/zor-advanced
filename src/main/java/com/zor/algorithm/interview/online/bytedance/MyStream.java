package com.zor.algorithm.interview.online.bytedance;

import java.io.OutputStream;

/**
 * 一个stream类，有output方法。可能有不同的输出信道file、net，
 * 也可能有不同的加密方式 aes、base64；
 * 设计一个base64加密的、file信道输出的 stream；
 * 设计一个aes加密、net信道输出的stream。
 *
 * @date 2021/2/5
 */
public class MyStream extends Stream {

    public MyStream(EncodeWay encodeWay, OutputTunnel outputTunnel) {
        super(encodeWay, outputTunnel);
    }

    @Override
    public void output(byte[] b) {
        super.output(b);
    }
}

class Stream {

    private final EncodeWay encodeWay;
    private final OutputTunnel outputTunnel;

    public Stream(EncodeWay encodeWay, OutputTunnel outputTunnel) {
        this.encodeWay = encodeWay;
        this.outputTunnel = outputTunnel;
    }

    public void output(byte[] b) {

        byte[] encode = encodeWay.encode(b);
        outputTunnel.write(encode);

    }


}

interface EncodeWay {

    byte[] encode(byte[] input);

}

interface OutputTunnel {

    void write(byte[] input);

}
