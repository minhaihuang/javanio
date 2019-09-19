package com.hhm.javanio.io.output;

import java.io.*;

/**
 * 基础的文件输出。可作为文件复制
 */
public class Out01BasicFileOutPut {

    public static void main(String[] args) throws IOException {
        fileCopy("C:/test/123.txt","C:/test/124.txt");
    }

    public static void fileCopy(String srcPath,String destPath) throws IOException {
        try (
                // 使用缓存流会显著增加性能
                BufferedReader br = new BufferedReader(
                        new FileReader(srcPath)
                );
                PrintWriter writer = new PrintWriter(
                        new BufferedWriter(new FileWriter(destPath))
                );
        ){
            String line;
            while ((line = br.readLine()) != null){
                writer.write(line);
            }
        }
    }
}
