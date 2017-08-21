package yz.learning.basic;

/**
 * float和double基本类型的比较
 *
 * @author 袁臻
 * 2017/08/21 13:09
 */
public class ComparisonOfFloatAndDouble {
    /**
     * 测试方法
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        float f1 = 0.1F;
        float f2 = 0.1F;

        System.out.println(Float.compare(f1, f2));

        double d1 = 0.1D;
        double d2 = 0.1D;

        System.out.println(Double.compare(d1, d2));
    }
}