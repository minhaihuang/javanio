package com.hhm.javanio.nio.filechannel;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * 通过IntBuffer操作ByteBuffer中的int型数据
 */
public class Nio06IntBufferDemo {
    public static void main(String[] args) {
        ByteBuffer buff = ByteBuffer.allocate(1024);
        IntBuffer intBuffer = buff.asIntBuffer();
        intBuffer.put(new int[]{11,23,3,42,5,6,7,8});
        System.out.println(intBuffer.get(3));
        intBuffer.put(3,45); // 替换指定位置的元素。索引从0开始
        intBuffer.flip();// 把结束标志设置到当前位置，然后把当前位置设为0
        while (intBuffer.hasRemaining()){ // hasRemaining() 获取当前位置到结束还有多少个元素
            int i = intBuffer.get(); // 获取当前位置的元素，并把位置加1
            System.out.println(intBuffer.position());
            System.out.println(i);
        }
    }
}
