package com.hhm.javanio.nio.socketchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 客户端
 */
public class MySocketChannelClient {
    private static final int BSIZE = 1024;
    public static void main(String[] args) {
        client();
    }

    /**
     * 客户端
     */
    public static void client(){
        // 分配缓冲器大小
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        try (
                // 开启客户端管道
                SocketChannel socketChannel = SocketChannel.open()
            ){
            // 配置为非阻塞式管道
            socketChannel.configureBlocking(false);
            // 连接服务器
            boolean connect = socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
            if(socketChannel.finishConnect()){// 已完成连接
                int i = 0;
                while (true) {
                    TimeUnit.MICROSECONDS.sleep(1);
                    String message = "I am message" + i++ ;
                    buff.clear(); // 清空管道
                    buff.put(message.getBytes());
                    buff.flip();
                    while (buff.hasRemaining()) {
                        socketChannel.write(buff);
                        System.out.println(Arrays.toString(buff.array()));
                    }
                    if(i == 100){
                        socketChannel.close();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
