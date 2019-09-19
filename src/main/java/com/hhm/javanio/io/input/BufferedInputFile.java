package com.hhm.javanio.io.input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 缓冲读取文件
 */
public class BufferedInputFile {
    public static void main(String[] args) throws IOException {
        readFile("C:/test/area.txt");
    }
    public static String readFile(String path) throws IOException {
        FileReader reader = new FileReader(path);
        BufferedReader br = new BufferedReader(reader);
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null){
            sb.append(line+"\n"); // readLine 会把文件中的换行符删除
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
