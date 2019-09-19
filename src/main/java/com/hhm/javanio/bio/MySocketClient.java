package com.hhm.javanio.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 客户端
 */
public class MySocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(8080));
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true); //设定自动flush

        //write方法需要 加上\r\n,这样服务器端，才会收到终止命令，不然就会一直读
//          out.write("hello 服务期2\r\n");
        out.println("逗比服务器");

    }
}
