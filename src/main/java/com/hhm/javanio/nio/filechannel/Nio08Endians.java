package com.hhm.javanio.nio.filechannel;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * 通过字节存放模式设置来改变字符中的字节次序
 */
public class Nio08Endians {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[12]);
        bb.asCharBuffer().put("abcedf");
        System.out.println(Arrays.toString(bb.array())); // [0, 97, 0, 98, 0, 99, 0, 101, 0, 100, 0, 102]

        // 高位优先
        bb.rewind();
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));

        // 低位优先
        bb.rewind();
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));
    }

}
