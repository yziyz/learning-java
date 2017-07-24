package yz.learning.jdbc;

import java.sql.*;

public class PostgreSqlJdbc {
    private static final String URL = "jdbc:postgresql://localhost:5432/TEST?user=postgres&password=123456";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(URL);

        Statement st = conn.createStatement();
        //st.execute("INSERT INTO test_table(id, content) VALUES(1,'t');");
        conn.setAutoCommit(true);

        ResultSet rs = st.executeQuery("SELECT * FROM test_table");
        while (rs.next()) {
            System.out.println(rs.getArray(2));
        }
        rs.close();
        st.close();
    }
}
