package com.hhm.javanio.io.input;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * 读取格式化数据。读取字节流。逐字节读取
 */
public class FormattedMemoryInput {
    public static void main(String[] args) throws IOException {
        try (
                DataInputStream in = new DataInputStream(
                        new ByteArrayInputStream("123456".getBytes())
                );
                ){
            // in.available(); 判断还有多少可以读取的字符
            while (in.available() != 0){
                System.out.println((char) in.readByte());// 逐字节读取
            }
        }
    }
}
