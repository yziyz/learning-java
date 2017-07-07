package yz.learn.reflection;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * Retrieving Class Objects describes the ways to get a Class
 *
 * http://docs.oracle.com/javase/tutorial/reflect/class/classNew.html
 */
public class RetrievingClassObjects {
    private static Object object01 = new Object() {
        public void m(){};
    };

    //java.lang.NullPointerException
    //private static Class class03 = object01.getClass().getDeclaringClass();
    private static Class class04 = object01.getClass().getEnclosingClass();
    enum E {A, B}

    public static void main(String[] args) {
        objectDotGetClass();
        theDotClassSynax();
        classDotForName();
        primitiveTypeWrappers();
        methodThatReturnClasses();
    }

    private static void objectDotGetClass() {
        /*
          If an instance of an object is available, then the simplest
          way to get its Class is to invoke Object.getClass(). Of
          course, this only works for reference types which all inherit
          from Object.
         */
        Class<?> stringClass = "foo".getClass();
        System.out.println(stringClass.getCanonicalName());

        /*
        There is a unique console associated with the virtual machine
        which is returned by the static method System.console(). The
        value returned by getClass() is the Class corresponding to
        java.io.Console.
         */
        //Class consoleClass = System.console().getClass();
        //System.out.println(consoleClass.getCanonicalName());

        Class<?> enumClass = E.A.getClass();
        System.out.println(enumClass.getCanonicalName());

        byte[] bytes = new byte[1024];
        Class<?> bytesClass = bytes.getClass();
        System.out.println(bytesClass.getCanonicalName());


        Set<String> stringSet = new HashSet<String>();
        Class<?> stringSetClass = stringSet.getClass();
        System.out.println(stringSetClass.getCanonicalName());
    }

    private static void theDotClassSynax() {
        //The .class Syntax

        /*
        If the type is available but there is no instance then it is
        possible to obtain a Class by appending ".class" to the name
        of the type. This is also the easiest way to obtain the Class
        for a primitive type.
         */
        boolean b;
        //compile-time error
        //Class bClass = b.getClass();
        Class<?> bClass = boolean.class;
        System.out.println(bClass.getCanonicalName());
        /*
        Note that the statement boolean.getClass() would produce a
        compile-time error because a boolean is a primitive type and
        cannot be dereferenced. The .class syntax returns the Class
        corresponding to the type boolean.
         */

        /*
        The variable printStringClass will be the Class corresponding to the type
        java.io.PrintStream.
         */
        Class<?> printStringClass = java.io.PrintStream.class;
        System.out.println(printStringClass.getCanonicalName());

        /*
        The .class syntax may be used to retrieve a Class corresponding
        to a multi-dimensional array of a given type.
         */
        Class<?> multiDimensionalArrayClass = int[][][].class;
        System.out.println(multiDimensionalArrayClass.getCanonicalName());
    }

    private static void classDotForName() {
        /*
        If the fully-qualified name of a class is available, it is
        possible to get the corresponding Class using the static method
        Class.forName(). This cannot be used for primitive types.The
        syntax for names of array classes is described by Class.getName().
        This syntax is applicable to references and primitive types.
         */
        try {
            //This statement will create a class from the given fully-qualified name.
            Class<?> regularExpressionClass = Class.forName("yz.learn.RegularExpression");
            System.out.println(regularExpressionClass.getCanonicalName());

            /*
            The variable cDoubleArray will contain the Class corresponding to an array
            of primitive type double (i.e. the same as double[].class). The cStringArray
            variable will contain the Class corresponding to a two-dimensional array of
            String (i.e. identical to String[][].class).
             */
            Class<?> doubleArrayClass = Class.forName("[D");
            System.out.println(doubleArrayClass.getCanonicalName());

            Class<?> stringArrayCLass = Class.forName("[[Ljava.lang.String;");
            System.out.println(stringArrayCLass.getCanonicalName());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void primitiveTypeWrappers() {
        /*
        The .class syntax is a more convenient and the preferred way to obtain the Class
        for a primitive type; however there is another way to acquire the Class. Each of
        the primitive types and void has a wrapper class in java.lang that is used for
        boxing of primitive types to reference types. Each wrapper class contains a
        field named TYPE which is equal to the Class for the primitive type being wrapped.
         */
        Class<?> doubleWrapperClass = Double.TYPE;
        System.out.println(doubleWrapperClass.getCanonicalName());
        /*
        There is a class java.lang.Double which is used to wrap the primitive type double
        whenever an Object is required. The value of Double.TYPE is identical to that of
        double.class.
         */

        /*
        Void.TYPE is identical to void.class
         */
        Class<?> voidClass = Void.TYPE;
        System.out.println(voidClass.getCanonicalName());
    }

    private static void methodThatReturnClasses() {
        /*
        Class.getSuperclass()
        Returns the super class for the given class.
         */
        Class<?> class01 = java.awt.event.ActionEvent.class.getSuperclass();
        System.out.println(class01.getCanonicalName());

        /*
        Class.getClasses()
        Returns all the public classes, interfaces, and enums that are
        members of the class including inherited members.
         */
        Class<?>[] classes01 = Character.class.getClasses();
        for (Class<?> c : classes01) {
            System.out.println(c.getCanonicalName());
        }

        /*
        Class.getDeclaredClasses()
        Returns all of the classes interfaces, and enums that are explicitly
        declared in this class.
         */

        Class<?>[] classes02 = Character.class.getDeclaredClasses();
        for (Class<?> c : classes02) {
            System.out.println(c.getCanonicalName());
        }

        try {
            /*
        Class.getDeclaringClass()
        java.lang.reflect.Field.getDeclaringClass()
        java.lang.reflect.Method.getDeclaringClass()
        java.lang.reflect.Constructor.getDeclaringClass()
        Returns the Class in which these members were declared. Anonymous
        Class Declarations will not have a declaring class but will have an
        enclosing class.
         */
            Field field01 = System.class.getField("out");
            //
            Class<?> class02 = field01.getDeclaringClass();
            System.out.println(class02.getCanonicalName());
            /*
            The field out is declared in System.
             */

            /*
            The declaring class of the anonymous class defined by o is null.
             */
            //System.out.println(class03.getCanonicalName());
            System.out.println(class04.getCanonicalName());

            /*
            Class.getEnclosingClass()
            Returns the immediately enclosing class of the class.
             */

            Class class05 = Thread.State.class.getEnclosingClass();
            System.out.println(class05.getCanonicalName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
