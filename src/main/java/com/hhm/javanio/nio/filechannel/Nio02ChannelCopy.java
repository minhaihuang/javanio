package com.hhm.javanio.nio.filechannel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 复制文件
 */
public class Nio02ChannelCopy {
    private static final int BSIZE = 1024;
    // 打开一个管道只用于读。打开另外一个用于写。
    public static void main(String[] args) throws IOException {
        FileChannel in = new FileInputStream("C:\\test\\testio\\2.txt").getChannel();
        FileChannel out = new FileOutputStream("C:\\test\\testio\\3.txt").getChannel();

        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        int len = 0;
        while ((len = in.read(buff)) != -1){
            buff.flip();
            out.write(buff);
            buff.clear(); // write操作之后，信息仍存在缓冲器中，接着clear操作则对所有内部指正重新安排，以便
            // 缓冲器在另一个read()操作期间能够做好接受数据的准备。
        }
        out.close();
        in.close();
    }
}
