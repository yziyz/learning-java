package yz.learning.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 袁臻
 * 2017/08/30 09:55
 */
public class RemoveDdlInSql {
    private static final String INSERT_TEMPLATE = "INSERT INTO %s VALUES %s;";
    private static final String patternString = String.format(INSERT_TEMPLATE, "\"public\".\"(.+?)\"", "(.+?)");
    private static final Pattern pattern = Pattern.compile(patternString);

    /**
     * 判断指定的一行字符串是否为INSERT语句
     * @param line 指定的一行字符串
     * @return 指定的一行字符串是否为INSERT语句
     */
    private static boolean isInsertStatement(String line) {
        return line.length() != 0 && line.charAt(0) == 'I';
    }

    /**
     * 根据指定的一行INSERT语句转化为符合ORACLE的语句
     * @param line 指定的一行INSERT语句
     * @return 符合ORACLE的语句
     */
    private static String convert(String line) {
        Matcher matcher = pattern.matcher(line);
        matcher.find();

        try {
            String tableName = matcher.group(1);
            String values = matcher.group(2);

            return String.format(INSERT_TEMPLATE, tableName, values).concat("\n");
        } catch (IllegalStateException e) {
            System.out.println(line);
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    private static void work(String filePath) throws IOException {
        String newFilePath = filePath.concat("-new");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFilePath, false), StandardCharsets.UTF_8))) {
            String line = null;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (isInsertStatement(line)) {
                    //若该行为INSERT语句，则写到新的文件
                    bw.write(convert(line));
                } else {
                    count++;
                }
            }
            System.out.println("Processed, new file path is: " + newFilePath);
            System.out.println("Remove lines count: " + count);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java RemoveDdlInSql path_to_sql_file");
            return;
        }
        //work(args[0]);
    }
}
