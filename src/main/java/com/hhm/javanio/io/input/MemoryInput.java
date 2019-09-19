package com.hhm.javanio.io.input;

import java.io.*;

/**
 * 从内存中输入。读取每一个字符，若是包含中文，会乱码
 */
public class MemoryInput {
    public static void main(String[] args) throws IOException {
        StringReader stringReader = new StringReader("123456777");
//        StringReader stringReader = new StringReader("123456777哈哈哈");
        int c;
        while ((c = stringReader.read()) != -1){
            System.out.println((char)c); // read是以int返回下一字节，因此必须通过类型转换为char才能正确打印
        }
    }
}
