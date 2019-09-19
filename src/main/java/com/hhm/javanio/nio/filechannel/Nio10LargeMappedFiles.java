package com.hhm.javanio.nio.filechannel;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 内存映射大文件。文件映射通常用于极大的文件
 */
public class Nio10LargeMappedFiles {
    private static final int LENGTH = 0x8FFFFFF; // 128M
    public static void main(String[] args) throws IOException {
        produceBigFile();
    }

    /**
     * 内存映射大文本
     * @throws IOException
     */
    public static void nioBigFile() throws IOException {
        // 获取文件管道
        FileChannel fc = new RandomAccessFile("C:/test/testio/test.dat", "rw").getChannel();
        // 获取缓冲器
        MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE,0,LENGTH);
        for (int i = 0; i < LENGTH; i++){
            out.put((byte)'x');
        }
        System.out.println("finish writing");
        for (int i = LENGTH/2; i < LENGTH/2 + 6; i++){
            System.out.println((char)out.get(i));
        }
    }

    /**
     * 普通流操作大文本
     * @throws IOException
     */
    public static void produceBigFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(
                new FileWriter("C:/test/testio/test2.txt")
        );
        for (int i = 0; i < LENGTH; i++){
            bw.write("1");
        }
        bw.flush();
        bw.close();
    }
}
