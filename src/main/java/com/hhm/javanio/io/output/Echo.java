package com.hhm.javanio.io.output;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 在屏幕上打印输入的每一行
 */
public class Echo {
    public static void main(String[] args) throws IOException {
        try (
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(System.in)
                )
        ){
            String s;
            System.out.println("please enter msg:");
            while ((s = br.readLine()) != null && s.trim().length() > 0){
                System.out.println(s);
                System.out.println("please enter msg:");
            }
        }
    }
}
