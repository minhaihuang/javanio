package com.hhm.javanio.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 阻塞io的服务端
 */
public class MySocketServer {
    static byte[] b = new byte[1024];

    public static void main(String[] args) throws IOException {
        receivedMessage();
    }

    /**
     * 接收和处理客户端请求信息
     * @throws IOException
     */
    public static void receivedMessage() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            System.out.println("waiting accept");
            Socket accept = serverSocket.accept();// 阻塞，等待客户端连接。
            System.out.println("accept success");

            // 每一个连接开启一个请求
            SocketServerHandler socketServerHandler = new SocketServerHandler(accept);
            Thread t = new Thread(socketServerHandler);
            t.start();
        }
    }
}
