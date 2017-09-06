package yz.learning.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 袁臻
 * 2017/08/30 17:28
 */
public class AddColumnsToInsert {
    /**
     * INSERT语句模板
     */
    private static final String INSERT_TEMPLATE = "INSERT INTO %s(%s) VALUES %s;";

    /**
     * INSERT语句正则模式
     */
    private static final Pattern PATTERN = Pattern.compile("INSERT INTO \"public\".\"(.+?)\" VALUES (.+?);");

    /**
     * 匹配数据的列
     */
    private static final String COLUMNS = "ID, CODE, DEPT_ID, DICT_TYPE, IS_DEFAULT, NAME, ORDER_NUMBER, REMARK, STATE, CREATED_AT, UPDATED_AT";

    /**
     * SESSION设定
     */
    private static final String SETTING = "ALTER SESSION SET NLS_DATE_FORMAT='YYYY-MM-DD'; \n" +
            "ALTER SESSION SET NLS_TIMESTAMP_FORMAT = 'YYYY-MM-DD HH24:MI:SS.FF';\n";

    /**
     * 根据指定的一行INSERT语句转化为符合ORACLE的语句
     *
     * @param line 指定的一行INSERT语句
     * @return 符合ORACLE的语句
     */
    private static String convert(String line) {
        Matcher matcher = PATTERN.matcher(line);
        matcher.find();
        //取出表名和数据
        String tableName = matcher.group(1);
        String values = matcher.group(2);
        //构造心得SQL并返回
        return String.format(INSERT_TEMPLATE, tableName, COLUMNS, values).concat("\n");
    }

    /**
     * 实际执行方法
     *
     * @param filePath 源文件路径
     * @throws IOException IOException
     */
    private static void work(String filePath) throws IOException {
        String newFilePath = filePath.concat("-new.sql");
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFilePath, false), StandardCharsets.UTF_8))
        ) {
            //向新文件写入SESSION设定
            bw.write(SETTING);
            //计数
            int count = 0;
            //缓存每行
            String line;
            //遍历源文件
            while ((line = br.readLine()) != null) {
                //转换每行并写入新文件
                bw.write(convert(line));
                count++;
            }
            //提交事物
            bw.write("COMMIT WORK;");
            System.out.println("Processed, new file path is: " + newFilePath);
            System.out.println("Lines count: " + count);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java AddColumnsToInsert path_to_sql_file");
            return;
        }
        //执行
        work(args[0]);
    }
}
