package com.hhm.javanio.nio.socketchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 服务端，利用serverSocketChannel和Selector（选择器）
 */
public class MySelectorServerSocket {
    private static final int BUF_SIZE = 1024;
    private static final int TIMEOUT = 3000;

    public static void main(String[] args) {
        selector();
    }

    private static void selector(){
        try (
                Selector selector = Selector.open();
                ServerSocketChannel ssc = ServerSocketChannel.open();
                ){
            // 非阻塞式管道
            ssc.configureBlocking(false);
            ssc.socket().bind(new InetSocketAddress(8080));
            // 注册到选择器
            ssc.register(selector,SelectionKey.OP_ACCEPT);

            while (true) {
                //System.out.println("size =" + selector.selectedKeys().size());

                if(selector.select(TIMEOUT) == 0){
                    // Thread.sleep(TIMEOUT);
                    System.out.println("==");
                    continue;
                }
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    if(key.isValid() && key.isAcceptable()){
                        handleAccept(key);
                    }
                    if(key.isValid() && key.isReadable()){
                        handleRead(key);
                    }
                    if(key.isValid() && key.isWritable()){
                        handleWrite(key);
                    }
                    if(key.isValid() && key.isConnectable()){
                        System.out.println("isConnectable = true");
                    }
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理连接
     * @param key
     */
    private static void handleAccept(SelectionKey key) throws IOException {
        // 服务端管道
        ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
        // 连接成功后返回一个客户端管道
        SocketChannel sc = ssc.accept();
        // 客户端管道为非阻塞管道
        sc.configureBlocking(false);
        sc.register(key.selector(),SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
    }

    /**
     * 处理接收客户端消息
     * @param key
     */
    private static void handleRead(SelectionKey key) throws IOException {
        // 获取通信管道
        SocketChannel sc = (SocketChannel)key.channel();
        // 获取缓存区
        ByteBuffer buf = (ByteBuffer) key.attachment();
        // 把数据读进缓冲区
        System.out.print(sc.hashCode()+"==> ");
        long bytesRead = sc.read(buf);
        while(bytesRead>0){
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char)buf.get());
            }
            System.out.println();
            buf.clear();
            bytesRead = sc.read(buf);
        }
        if(bytesRead == -1){
            sc.close();
        }
    }

    /**
     * 处理返回数据
     * @param key
     */
    public static void handleWrite(SelectionKey key) throws IOException {
        // 获取管道
        SocketChannel socketChannel = (SocketChannel) key.channel();
        // 获取缓存数据
        ByteBuffer buff = (ByteBuffer) key.attachment();
        // 写数据
        buff.flip();
        while (buff.hasRemaining()) {
            socketChannel.write(buff);
        }
        buff.compact();
    }
}
