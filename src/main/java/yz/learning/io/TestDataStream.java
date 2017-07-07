package yz.learning.io;

import java.io.*;

/**
 * Created by yuanzhen on 6/2/17.
 */
public class TestDataStream {
    public static void main(String[] args) throws IOException{
        try (DataOutputStream outout = new DataOutputStream(new FileOutputStream("temp.dat"))){
            outout.writeUTF("John");
            outout.writeInt(34);
            outout.writeUTF("Susan");
            outout.writeInt(35);
            outout.writeUTF("Kim");
            outout.writeInt(36);
        }
        try (DataInputStream input = new DataInputStream(new FileInputStream("temp.dat"))){
            System.out.println(input.readUTF() + " " + input.readInt());
            System.out.println(input.readUTF() + " " + input.readInt());
            System.out.println(input.readUTF() + " " + input.readInt());
        }
    }
}
