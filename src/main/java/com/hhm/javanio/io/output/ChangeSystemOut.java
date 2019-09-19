package com.hhm.javanio.io.output;

import java.io.PrintWriter;

public class ChangeSystemOut {
    public static void main(String[] args) {
        /**
         * 重新封装system.out.
         */
        PrintWriter printWriter = new PrintWriter(System.out,true);
        //printWriter.print(1111); // 这个不生效
        printWriter.println(111);
        System.out.print(111);
    }
}
