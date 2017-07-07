package yz.learn.io;

import java.io.*;

/**
 * 实现一个复制文件功能的小工具。
 */
public class Copy {
    public static void main(String[] args) throws IOException{
        if (args.length != 2){
            System.out.println("Usage: java Copy sourceFile targetFile");
            System.exit(1);
        }

        File sourceFile = new File(args[0]);
        if (!sourceFile.exists()){
            System.out.printf("Source file: %s dose not exist.\n", args[0]);
            System.exit(2);
        }

        File targetFile = new File(args[1]);
        if (targetFile.exists()){
            System.out.printf("Target file: %s exist.\n", args[1]);
            System.exit(3);
        }

        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(sourceFile));
             BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(targetFile));
             ){
            int r, numberOfBytesCopied = 0;
            while ((r = input.read()) != -1){
                //System.out.println(r);
                output.write(r);
                numberOfBytesCopied++;
            }

            System.out.printf("%d bytes copied.\n", numberOfBytesCopied);
        }
    }
}
