package com.hhm.javanio.nio.filechannel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * 转换数据，缓冲区里面的数据转换为字符输出。
 * 注意：缓冲器容乃的是普通字节，为了把它们转换成字符，我们要么在输入的时候对其进行编码，要么在
 * 将其从缓冲器输出时对它们进行解码。这样的输出才有意义
 */
public class Nio04BufferToText {
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
//        test01();
//        test02();
        test03();
    }

    /**
     * 使用CharBuffer输入和输出
     */
    public static void test03() throws IOException {
        // 写
        FileChannel fc = new FileOutputStream("C:/test/testio/6.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(24);
        buff.asCharBuffer().put("some text111");
        fc.write(buff);
        fc.close();
        // 读
        FileChannel in = new FileInputStream("C:/test/testio/6.txt").getChannel();
        ByteBuffer readBuff = ByteBuffer.allocate(BSIZE);
        in.read(readBuff);
        readBuff.flip();
        System.out.println(readBuff.asCharBuffer());
    }

    /**
     * 输入时指定编码
     */
    public static void test02() throws IOException {
        // 输出到文件
        FileChannel fc = new FileOutputStream("C:/test/testio/6.txt").getChannel();
        fc.write(ByteBuffer.wrap("some text".getBytes("UTF-16BE")));
        fc.close();

        FileChannel in = new FileInputStream("C:/test/testio/6.txt").getChannel();
        // 只读设置，设置缓冲区大小
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        // 将数据读进缓冲器
        in.read(buff);
        // 准备好已读的数据
        buff.flip();
        System.out.println(buff.asCharBuffer());
    }

    /**
     * 输出时设置编码
     * @throws IOException
     */
    public static void test01() throws IOException {
        // 输出到文件
        FileChannel fc = new FileOutputStream("C:/test/testio/6.txt").getChannel();
        fc.write(ByteBuffer.wrap("some text".getBytes()));
        fc.close();

        FileChannel in = new FileInputStream("C:/test/testio/6.txt").getChannel();
        // 只读设置，设置缓冲区大小
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        // 将数据读进缓冲器
        in.read(buff);
        // 准备好已读的数据
        buff.flip();
        // Doesn't work
        System.out.println(buff.asCharBuffer()); // 会乱码

        buff.rewind(); // 让缓冲器的游标重置指向0，返回到缓冲器的数据开始部分

        // work
        String encoding = System.getProperty("file.encoding");
        System.out.println("Decoding using"+encoding+":"+ Charset.forName(encoding).decode(buff));
        in.close();

        buff.rewind();
    }
}
