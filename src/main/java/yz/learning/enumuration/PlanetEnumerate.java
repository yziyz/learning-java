package yz.learning.enumuration;

/**
 * Java编程语言枚举类型比其他语言的类型更强大。
 * 枚举声明定义一个类（称为枚举类型）。
 * 例如，来自“星球”类的示例代码遍历太阳系中的所有行星。
 * 所有枚举都会隐式扩展java.lang.Enum。因为一个类只能扩展一个父类，所以Java语言不支持多个继承状态，因此枚举不能扩展其他任何东西。
 */
public enum PlanetEnumerate {
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.976e+24, 6.37814e6),
    MARS(6.421e+23, 3.3972e6),
    JUPITER(1.9e+27, 7.1492e7),
    SATURN(5.688e+26, 6.0268e7),
    URANUS(8.686e+25, 2.5559e7),
    NEPTUNE(1.024e+26, 2.4746e7);

    //universal gravitational constant
    public static final double G = 6.67300E-11;
    private final double mass;//in kilograms
    private final double radius;//in meters

    /**
     * 枚举类型的构造方法必须是包私有或私有访问。它会自动创建在枚举正文开头定义的常量。你不能自己调用枚举构造函数。
     */
    PlanetEnumerate(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Planet Enumerate <earth_weight>");
        }
        double earthWeight = Double.parseDouble(args[0]);
        double mass = earthWeight / EARTH.surfaceGravity();
        /*
        编译器在创建枚举时会自动添加一些特殊方法。
        例如，它们具有一个静态值方法，它返回一个包含枚举所有值的数组，并按它们被声明的顺序。
        该方法通常与for-each结构组合使用以迭代枚举类型的值。
         */
        for (PlanetEnumerate planet : PlanetEnumerate.values()) {
            System.out.printf("Your weight on %s is %f%n",
                    planet, planet.surfaceWeight(mass));
        }
    }

    //枚举类体可以包括方法和其他字段。
    private double mass() {
        return mass;
    }

    private double radius() {
        return radius;
    }

    double surfaceGravity() {
        return G * mass / (radius * radius);
    }

    double surfaceWeight(double otherMass) {
        return otherMass * surfaceGravity();
    }
}