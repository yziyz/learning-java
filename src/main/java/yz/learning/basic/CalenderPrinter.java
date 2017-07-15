package yz.learning.basic;

public class CalenderPrinter {
    /**
     * 判断指定的年数是否为闰年
     *
     * @param year 年数
     * @return 指定的年数是否为闰年
     */
    private static boolean isLeapYear(final int year) {
        if (year < 1900) {
            throw new IllegalArgumentException("Invalid year: " + year);
        }
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * 返回指定天数是星期几
     *
     * @param days 1970年i月1日到现在的天数
     * @return 指定天数是星期几
     */
    private static int getWeekDay(final int days) {
        return days % 7;
    }

    private static String getWeekDayInString(final int weekDay) {
        if (weekDay < 1 || weekDay > 7) {
            throw new IllegalArgumentException("Invalid week day: " + weekDay);
        }
        switch (weekDay) {
            case 1:
                return "Monday";
            case 2:
                return "TuesDay";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 7:
                return "Sunday";
            default:
                return null;
        }
    }

    /**
     * 获取指定月份的天数
     *
     * @param month      指定月份
     * @param isLeapYear 是否是闰年
     * @return 指定月份的天数
     */
    private static int getNumberOfDaysInMonth(final int month, final boolean isLeapYear) {
        int numDays = 0;

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                numDays = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                numDays = 30;
                break;
            case 2:
                if (isLeapYear)
                    numDays = 29;
                else
                    numDays = 28;
                break;
            default:
                System.out.println("Invalid month.");
                break;
        }
        return numDays;
    }

    /**
     * 获取指定年份的天数
     *
     * @param year 指定年份
     * @return 指定年份的天数
     */
    private static int getNumberOfDaysInYears(final int year) {
        int numberOfDaysInYears = 0;
        final boolean isLeapYear = isLeapYear(year);
        for (int i = 1; i <= 12; i++) {
            numberOfDaysInYears += getNumberOfDaysInMonth(i, isLeapYear);
        }
        return numberOfDaysInYears;
    }

    /**
     * 获取从1970年1月1日至指定日期的天数
     *
     * @param year  指定年份
     * @param month 指定月份
     * @param day   指定日
     * @return 从1970年1月1日至指定日期的天数
     */
    private static int getDays(final int year, final int month, final int day) {
        int numberOfDaysInYears = 0;

        //累加天数从1900年1月1日至指定年份的前一年的最后一天
        for (int i = 1900; i < year; i++) {
            numberOfDaysInYears += getNumberOfDaysInYears(i);
        }

        //累加天数至指定月份的前一月的最后一天
        final boolean isLeapYear = isLeapYear(year);
        for (int i = 1; i < month; i++) {
            numberOfDaysInYears += getNumberOfDaysInMonth(i, isLeapYear);
        }

        //累计天数至指定日的天数
        numberOfDaysInYears += day - 1;
        return numberOfDaysInYears;
    }

    /**
     * 打印
     *
     * @param year  指定年份
     * @param month 指定月份
     * @param day   指定日
     */
    public static void print(final int year, final int month, final int day) {
        final boolean isLeapYear = isLeapYear(year);
        final int daysToFirstDayOfMonth = getDays(year, month, 1);
        final int weekDay = getWeekDay(daysToFirstDayOfMonth);
        final int daysInMonth = getNumberOfDaysInMonth(month, isLeapYear);

        //打印表头
        System.out.println("***************************");
        System.out.printf("Today is %s\n", getWeekDayInString((weekDay + day) % 7));
        System.out.println("***************************");

        //打印日历
        System.out.println("Mon Tue Wed Thu Fri Sat Sun");

        int count = 0;
        //打印空白
        for (int i = 1; i <= weekDay; i++) {
            System.out.print("    ");
            count++;
        }

        //打印日
        for (int i = 1; i <= daysInMonth; i++) {
            System.out.printf("%2d  ", i);
            count++;
            if (count % 7 == 0) {
                System.out.printf("\n");
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java CalenderPrinter YEAR MONTH DAY");
            return;
        }

        final int year = Integer.parseInt(args[0]);
        final int month = Integer.parseInt(args[1]);
        final int day = Integer.parseInt(args[2]);

        print(year, month, day);
    }
}
