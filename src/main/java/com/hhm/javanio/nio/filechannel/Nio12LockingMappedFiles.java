package com.hhm.javanio.nio.filechannel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 对部分文件加锁。
 * 文件映射通常应用于极大的文件。
 * tryLock(long position, long length, boolean shared) 对文件的部分内容加锁。第三个参数表示锁是否是共享锁
 */
public class Nio12LockingMappedFiles {
    private static final int LENGTH = 0x8FFFFFF; // 128M
    private static FileChannel fc;

    public static void main(String[] args) throws IOException {
        fc = new RandomAccessFile("C:/test/testio/test.dat","rw").getChannel();
        MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE,0,LENGTH);
        for (int i = 0; i < LENGTH; i++){
            out.put((byte)'x');
        }
        new LockAndModify(out,0,0+LENGTH/3).start();
        new LockAndModify(out,LENGTH/2,LENGTH/2+LENGTH/4).start();
    }

    private static class LockAndModify extends Thread{
        private ByteBuffer buffer;
        private int start,end;

        LockAndModify(ByteBuffer buffer,int start,int end){
            this.start = start;
            this.end = end;
            buffer.limit(end);
            buffer.position(start);
            this.buffer = buffer.slice();//
        }

        @Override
        public void run() {
            try {
                FileLock fl = fc.lock(start,end,false);
                System.out.println("Locked:"+ start + " to "+ end);
                while (buffer.position() < buffer.limit() -1){
                    buffer.put((byte)(buffer.get() + 1));
                }
                // 释放锁
                fl.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
