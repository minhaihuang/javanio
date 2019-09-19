package com.hhm.javanio.nio.filechannel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 获取产生可写的，可读写的以及可读的通道
 */
public class Nio01GetChannel {
    public static void main(String[] args) throws IOException {
        // Channel也是继承了AutoCloseAble。可用try-with-resources来关闭流
        // ByteBuffer字节流，只能操作字节数据
        // 可写的通道
        FileChannel fc = new FileOutputStream("C:\\test\\testio\\2.txt").getChannel();
        fc.write(ByteBuffer.wrap("123h".getBytes()));
        fc.close();
        // 可读可写的通道
        fc = new RandomAccessFile("C:\\test\\testio\\2.txt","rw").getChannel(); // r-读，w-写，rw-读写
        fc.position(fc.size());// 读操作，move to the end of file
        fc.write(ByteBuffer.wrap("234".getBytes()));
        fc.close();
        // 可读通道
        fc = new FileInputStream("C:\\test\\testio\\2.txt").getChannel();
        /**
         * 对于只读访问，为空快速访问数据，必须显示地使用静态的allocate方法来分配ByteBuffered；
            在实际应用中，1024字节可能会很小，根据实际情况选择大小。
         allocateDirect可能更快，但是需要更大的开销。可根据实际情况使用**/
        ByteBuffer buff = ByteBuffer.allocate(1024);

        fc.read(buff);
        buff.flip(); // 一旦调用read()方法后，就必须调用缓冲器上的flip()，让它做好让别读取字节的准备。这样做是为了获取最大速度
        while (buff.hasRemaining()) {
            System.out.println((char) buff.get());
        }
        fc.close();
    }
}
