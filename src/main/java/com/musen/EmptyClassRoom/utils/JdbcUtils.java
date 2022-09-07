package com.musen.EmptyClassRoom.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类，用以创建与Mysql的连接和关闭连接
 */
public class JdbcUtils {
    /**
     * 获取连接
     *
     * @return Connection
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("static/application.properties");
        Properties pros = new Properties();
        pros.load(is);

        String url = pros.getProperty("spring.datasource.url");
        String user = pros.getProperty("spring.datasource.username");
        String password = pros.getProperty("spring.datasource.password");
        String driverClass = pros.getProperty("spring.datasource.driver-class-name");

        Class.forName(driverClass);
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 关闭连接和PreparedStatement
     *
     * @param conn
     * @throws SQLException
     */
    public static void closeConnection(Connection conn, PreparedStatement ps) throws SQLException {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭资源操作
     *
     * @param conn
     * @param ps
     * @param rs
     */
    public static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
