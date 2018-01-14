package yz.learning.jdbc;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author 袁臻
 * 2018/01/14 10:52
 */
public class SqlParamTest {

    public static void main(String[] args) {
        List<Double> ages = Arrays.asList(10.0, 11.0, 12.0);
        List<String> names = Arrays.asList("ming", "lei");
        String sql = doWith("SELECT * FROM students WHERE name IN ? AND age IN ?", names, ages);
        System.out.println(sql);
    }

    /**
     * 将“以'?'为参数占位符的SQL字符串”转为对应的字符串，没有防范"SQL注入"
     *
     * @param sql   SQL字符串
     * @param param 参数列表
     * @return 对应的字符串
     */
    private static String doWith(final String sql, final Object... param) {
        //用"?"分割sql字符串
        final String[] segments = sql.split("\\?");
        //检查sql段长度(既是"?"个数)是否是否等于参数数组长度
        if (param.length != segments.length) {
            throw new IllegalArgumentException("参数个数不匹配");
        }
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < segments.length; i++) {
            //在"?"的位置写入paramToString
            stringBuilder.append(segments[i])
                    .append(paramToString(param[i]));
        }
        //返回
        return stringBuilder.toString();
    }

    /**
     * 将参数转为对应的SQL字符串
     * 参考链接https://stackoverflow.com/questions/37156447/unchecked-cast-java-lang-object-to-java-util-list
     *
     * @param param 参数实例
     * @return 对应的SQL字符串
     */
    private static String paramToString(Object param) {
        if (param instanceof Number) {
            //若是数字，则直接返回其toString
            return param.toString();
        } else if (param instanceof Collection) {
            //若是集合，调用collectionToString
            return collectionToString((Collection<?>) param);
        } else {
            //其他，调用其toString，并在前后添加单引号
            return String.format("'%s'", param.toString());
        }
    }

    /**
     * 集合对象转为对应的SQL字符串
     *
     * @param collection 集合对象
     * @return 对应的SQL字符串
     */
    private static String collectionToString(Collection<?> collection) {
        final StringBuilder stringBuilder = new StringBuilder("(");
        collection.forEach(param -> stringBuilder.append(',').append(paramToString(param)));
        return stringBuilder.deleteCharAt(1).append(')').toString();
    }
}
