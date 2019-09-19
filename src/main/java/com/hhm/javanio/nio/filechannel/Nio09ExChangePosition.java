package com.hhm.javanio.nio.filechannel;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * 交换相邻字符。
 * mark()。将mark为position
 * reset()。将position指到上次的mark。
 */
public class Nio09ExChangePosition {
    public static void main(String[] args) {
        String str = "UsingBuffers";
        char[] arr = str.toCharArray();
        ByteBuffer buffer = ByteBuffer.allocate(arr.length * 2); // 一个char两个字节
        CharBuffer cb = buffer.asCharBuffer();
        cb.put(arr);

        cb.rewind();
        print(cb);

        // 交换
        cb.rewind();
        exChange(cb);
        cb.rewind();
        print(cb);

        // 再次交换
        cb.rewind();
        exChange(cb);
        cb.rewind();
        print(cb);
    }

    public static void print(CharBuffer charBuffer){
        while (charBuffer.hasRemaining()){
           // System.out.print(charBuffer.position()+",");
            char c = charBuffer.get();
            System.out.print(c);
        }
        System.out.println();
    }

    /**
     * 交换相邻字符
     * @param buffer
     */
    public static void exChange(CharBuffer buffer){
        while (buffer.hasRemaining()){
            // mark位置
            buffer.mark();
            // 获取相邻两个字符
            char c1 = buffer.get();
            char c2 = buffer.get();
            // 重新设置position
            buffer.reset();
            // 交换。position会随之改变
            buffer.put(c2).put(c1);
        }
    }
}
