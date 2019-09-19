package com.hhm.javanio.nio.filechannel;

import java.nio.ByteBuffer;

/**
 * 获取基本数据类型数据
 */
public class Nio05GetData {
    private static final int BSIZE = 1024;
    public static void main(String[] args) {
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        buff.rewind();
        buff.asCharBuffer().put("hello");
        char c;
        while ((c = buff.getChar()) != 0){
            System.out.println(c);
        }
        System.out.print("");
        buff.rewind();

        buff.asIntBuffer().put(2);
        System.out.println(buff.getInt());

        buff.rewind();
        buff.asShortBuffer().put((short) 471142); // 超出了short的范围，会溢出取值
        System.out.println(buff.getShort()); // 12390

        buff.rewind();
        buff.asLongBuffer().put(11110);
        System.out.println(buff.getLong());

        buff.rewind();
        buff.asFloatBuffer().put(11111110F);
        System.out.println(buff.getFloat());

        buff.rewind();
        buff.asDoubleBuffer().put(11111111112D);
        System.out.println(buff.getDouble());
    }
}
