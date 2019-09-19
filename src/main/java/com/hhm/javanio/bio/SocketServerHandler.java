package com.hhm.javanio.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 处理socketServer接收到的信息
 */
public class SocketServerHandler implements Runnable{
    private Socket socket;
    public SocketServerHandler(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        byte[] b = new byte[1024];
        System.out.println("waiting message");
        try ( InputStream inputStream = socket.getInputStream();){
            int len = inputStream.read(b); // 再次阻塞，等待客户端发送消息
            System.out.println("message success");
            System.out.println(new String(b,0,len));
        } catch (IOException e) {
           e.printStackTrace();
        }

    }
}
