package yz.learning.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

import static java.lang.System.out;

enum ClassMember {CONSTRUCTOR, FIELD, METHOD, CLASS, ALL}

/**
 * Given a class name and an indication of which members are of interest,
 * the ClassSpy example uses the get*s() methods to determine the list of
 * all public elements, including any which are inherited.
 */
public class ClassSpy {
    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName(args[0]);
            out.format("Class:%n %s%n%n", c.getCanonicalName());

            Package p = c.getPackage();
            out.format("Package:%n  %s%n%n",
                    (p != null ? p.getName() : "-- No Package --"));

            for (int i = 1; i < args.length; i++) {
                switch (ClassMember.valueOf(args[i])) {
                    case CONSTRUCTOR:
                        printMembers(c.getConstructors(), "Constructor");
                        break;
                    case FIELD:
                        printMembers(c.getFields(), "Field");
                        break;
                    case METHOD:
                        printMembers(c.getMethods(), "Method");
                        break;
                    case CLASS:
                        printClasses(c);
                    case ALL:
                        printMembers(c.getConstructors(), "Constructors");
                        printMembers(c.getFields(), "Fields");
                        printMembers(c.getMethods(), "Methods");
                        printClasses(c);
                        break;
                    default:
                        assert false;
                }
            }
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }

    private static void printMembers(Member[] mbrs, String s) {
        out.format("%s:%n", s);
        for (Member mbr : mbrs) {
            if (mbr instanceof Field) {
                out.format("  %s%n", ((Field) mbr).toGenericString());
            } else if (mbr instanceof Constructor) {
                out.format("  %s%n", ((Constructor) mbr).toGenericString());
            } else if (mbr instanceof Method) {
                out.format("  %s%n", ((Method) mbr).toGenericString());
            }
        }
        if (mbrs.length == 0) {
            out.format("  -- No %s --%n", s);
        }
        out.format("%n");
    }

    private static void printClasses(Class<?> c) {
        out.format("Classes:%n");
        Class<?>[] clss = c.getClasses();
        for (Class<?> cls : clss) {
            out.format("  %s%n", cls.getCanonicalName());
        }
        if (clss.length == 0) {
            out.format("  -- No member interfaces, classes, or enums --%n");
        }

        out.format("%n");
    }
}

