package yz.learning.reflection;

/**
 * InstantiationException when the Constructor is Not Accessible
 */
class Cls {
    private Cls() {
    }
}

public class ClassTrouble {
    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName("Cls");
            c.newInstance(); //InstantiationException

        } catch (InstantiationException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }
}
