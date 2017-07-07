package yz.learn.reflection;

import java.lang.reflect.Method;
/**
 * Troubleshooting describes common errors encountered when using Class
 */
public class Troubleshooting {
    public static void main(String[] args){
        compilerWarning();
    }

    /*
    Compiler Warning: "Note: ... uses unchecked or unsafe operations"
     */
    private static void compilerWarning(){
        try {
            //Class c = Troubleshooting.class;
            Class<?> c = Troubleshooting.class;
            Method m = c.getMethod("m");
        } catch (NoSuchMethodException x) {
            x.printStackTrace();
        }
    }

    void m(){}
}
