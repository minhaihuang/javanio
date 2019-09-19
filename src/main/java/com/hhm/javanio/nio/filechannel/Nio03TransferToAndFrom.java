package com.hhm.javanio.nio.filechannel;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * 特殊方法transferTo()和transferFrom()允许我们将一个通道可另外一个通道直接连接
 */
public class Nio03TransferToAndFrom {
    public static void main(String[] args) throws IOException {
        String srcPath = "C:\\test\\testio\\2.txt";
//        String destPath = "C:\\test\\testio\\4.txt";
        String destPath = "C:\\test\\testio\\5.txt";
        fileCopy(srcPath,destPath);
    }

    public static void fileCopy(String srcPath, String destPath) throws IOException {
        try (
                FileChannel in = new FileInputStream(srcPath).getChannel();
                FileChannel out = new FileOutputStream(destPath).getChannel();
                ){
            in.transferTo(0,in.size(),out);
            // 或者
            // out.transferFrom(in,0,in.size());
        }
    }
}
