package yz.learning.reflection;

import java.lang.reflect.Method;

/**
 * Compiler Warning: "Note: ... uses unchecked or unsafe operations"
 */
public class ClassWarning {
    void m() {
        try {
            Class<?> c = ClassWarning.class;
            Method m = c.getMethod("m");
        } catch (NoSuchMethodException x) {
            x.printStackTrace();
        }
    }
}
