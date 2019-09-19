package com.hhm.javanio.io.output;

import java.io.*;

/**
 * 存储和恢复数据
 * DataInputStream和DataOutputStream，字节流，可以将各种基本数据类型以及String对象格式化输出到流中。
 */
public class Out02StoringAndRecoveringData {
    public static void main(String[] args) throws IOException{
       try (
               DataOutputStream out = new DataOutputStream(
                       new BufferedOutputStream(
                               new FileOutputStream("C:/test/testio/1.txt")
                       )
               );
               DataInputStream in = new DataInputStream(
                       new BufferedInputStream(
                               new FileInputStream("C:/test/testio/1.txt")
                       )
               )
       ){
            out.writeDouble(3.14159);
            out.writeUTF("That was pi");
            out.writeDouble(1.41413);
           out.writeUTF("Square root of 2");
           out.close();
           System.out.println(in.readDouble());
           System.out.println(in.readUTF());
           System.out.println(in.readDouble());
           System.out.println(in.readUTF());
       }
    }
}
