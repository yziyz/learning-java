package yz.learning.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * 如何向一个已经存在的文本文件追加数据。
 */
public class TestPrintWriter {
    public static void main(String[] args) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileOutputStream("temp.dat", true));
            printWriter.append("printWriter");
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
