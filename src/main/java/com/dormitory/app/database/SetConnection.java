package com.dormitory.app.database;

import org.hsqldb.jdbc.JDBCDriver;

import java.sql.Connection;
import java.sql.DriverManager;

public class SetConnection {
    // static String url = "jdbc:hsqldb:file:db;hsqldb.lock_file=false";
    // static JDBCDriver driver = new org.hsqldb.jdbc.JDBCDriver();

    public static Connection getConnection(){
        Connection connection = null;
        try {
            String url = "jdbc:mysql://mysql69.hostland.ru:3306/host1656712_testdb?serverTimezone=Europe/Moscow";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            connection = DriverManager.getConnection(url, "host1656712_test", "D0qZYJcm");
        }
        catch (Exception e) {
            throw new RuntimeException("Увы, драйвер соединения с MySQL не работает");
        }
        return connection;
    }
}
