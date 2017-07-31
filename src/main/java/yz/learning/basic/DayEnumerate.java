package yz.learning.basic;

/**
 * 枚举类型
 */
enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}

/**
 * @author 袁臻
 * 2017/07/31
 * 枚举
 * 枚举类型是一种特殊的数据类型，可使变量成为一组预定义的常量。
 * 变量必须等于为其预定义的值之一。
 * 常见的例子包括罗盘方向（NORTH，SOUTH，EAST和WEST的值）和星期几。
 */
public class DayEnumerate {
    /**
     * 一个枚举类型的域
     */
    Day day;

    /**
     * 构造方法
     *
     * @param day 枚举类型的域
     */
    public DayEnumerate(Day day) {
        this.day = day;
    }

    /**
     * 测试方法
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        DayEnumerate fistDay = new DayEnumerate(Day.FRIDAY);
        fistDay.tellItLikeItIs();
        DayEnumerate thirdDay = new DayEnumerate(Day.WEDNESDAY);
        thirdDay.tellItLikeItIs();
        DayEnumerate fifthDay = new DayEnumerate(Day.FRIDAY);
        fifthDay.tellItLikeItIs();
        DayEnumerate sixthDay = new DayEnumerate(Day.SATURDAY);
        sixthDay.tellItLikeItIs();
        DayEnumerate seventhDay = new DayEnumerate(Day.SUNDAY);
        seventhDay.tellItLikeItIs();
    }

    /**
     * 根据本对象的day输出相应的字符串
     */
    public void tellItLikeItIs() {
        switch (day) {
            case MONDAY:
                System.out.println("Mondays are bad");
                break;
            case FRIDAY:
                System.out.println("Fridays are better.");
                break;

            case SATURDAY:
            case SUNDAY:
                System.out.println("Weekends are best.");
                break;

            default:
                System.out.println("Midweek days are so-so.");
                break;
        }
    }
}