package yz.learning.io;

import java.io.*;

/**
 * 测试ObjectOutputStream
 */
public class TestObjectOutputStream {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("object.dat"))){
            output.writeUTF("你好");
            output.writeObject(new java.util.Date());
        }

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("object.dat"))){
            System.out.println(input.readUTF());
            java.util.Date date = (java.util.Date) input.readObject();
            System.out.println(date.toString());
        }
    }
}
