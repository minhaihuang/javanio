package com.hhm.javanio.nio.filechannel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * 文件加锁。
 * 当多个线程同时操作一个文件时，会引起文件的冲突。使用文件加锁可以解决这一问题。
 * JDK1.4以后引入了文件加锁机制。java的文件加锁直接映射到了本地操作系统的加锁工具。
 *
 * tryLock()。非阻塞式，设法获取锁，如果不能获得，直接返回。
 * lock()。阻塞式，直至获取到锁为止，或调用lock的线程中断，或调用lock的通道关闭。
 * FileLock.release()。释放锁
 * tryLock(long position, long length, boolean shared) 对文件的部分内容加锁。第三个参数表示锁是否是共享锁
 *
 * 可通过FIleLock.isShared()来查询锁的类型
 *
 * 无参数锁会对整个文件加锁
 *
 * SocketChannel、DatagramChannel和ServerSocketChannel不需要加锁，因为它们是单进程实体继承而来，我们通常不在两个
 * 进程之间共享socket
 */
public class Nio11FileLocking {
    public static void main(String[] args) throws IOException, InterruptedException {
        lockFile();
    }

    /**
     * 锁住文件
     * @throws IOException
     * @throws InterruptedException
     */
    public static void lockFile() throws IOException, InterruptedException {
        FileOutputStream out = new FileOutputStream("C:/test/testio/test3.txt");
        FileChannel fc = out.getChannel();
        // 尝试锁住文件
        FileLock fileLock = fc.tryLock();
        if(fileLock != null){
            System.out.println("lock file success");
            TimeUnit.MICROSECONDS.sleep(100); // 休眠
            // 释放锁
            fileLock.release();
            System.out.println("release file lock");
        }
        out.close();

    }
}
