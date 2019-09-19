package com.hhm.javanio.nio.filechannel;

import java.nio.*;

/**
 * 视图缓冲器。一旦底层的ByteBuffer通过视图缓冲器填满了整数或者其他基本类型时，就可以直接被写到通道中了。
 * 就可以转为成其他的基本数据类型
 */
public class Nio07ViewBuffers {
    public static void main(String[] args) {
        ByteBuffer buff = ByteBuffer.wrap(new byte[]{0,0,0,0,0,0,0,'a'});
        buff.rewind();
        System.out.println("Byte buffer");
        while (buff.hasRemaining()){
            System.out.print(buff.position()+"-->"+buff.get()+",");
        }
        System.out.println();
        CharBuffer cb = ((ByteBuffer)buff.rewind()).asCharBuffer();
        while (cb.hasRemaining()){
            System.out.print(cb.position()+"-->"+ cb.get() +","); // 0--> ,1--> ,2--> ,3-->a, 一个char两个字节
        }

        System.out.println();
        FloatBuffer fb = ((ByteBuffer)buff.rewind()).asFloatBuffer();
        while (fb.hasRemaining()){
            System.out.print(fb.position()+"-->"+ fb.get() +","); // 0-->0.0,1-->1.36E-43, 一个float四个字节
        }

        System.out.println();
        IntBuffer ib = ((ByteBuffer)buff.rewind()).asIntBuffer();
        while (ib.hasRemaining()){
            System.out.print(ib.position()+"-->"+ ib.get() +","); // 0-->0,1-->97, 一个int四个字节
        }

        System.out.println();
        LongBuffer lb = ((ByteBuffer)buff.rewind()).asLongBuffer();
        while (lb.hasRemaining()){
            System.out.print(lb.position()+"-->"+ lb.get() +","); // 0-->97, 一个long8个字节
        }

        System.out.println();
        ShortBuffer sb = ((ByteBuffer)buff.rewind()).asShortBuffer();
        while (sb.hasRemaining()){
            System.out.print(sb.position()+"-->"+ sb.get() +","); // 0-->0,1-->0,2-->0,3-->97, 一个short字节
        }

        System.out.println();
        DoubleBuffer db = ((ByteBuffer)buff.rewind()).asDoubleBuffer();
        while (db.hasRemaining()){
            System.out.print(db.position()+"-->"+ db.get() +","); // 0-->4.8E-322, 一个double8个字节
        }
    }
}
