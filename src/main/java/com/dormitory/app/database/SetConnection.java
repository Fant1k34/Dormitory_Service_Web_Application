package com.dormitory.app.database;

import org.hsqldb.jdbc.JDBCDriver;

import java.sql.Connection;
import java.sql.DriverManager;

public class SetConnection {

    /*
    String url = "jdbc:mysql://mysql69.hostland.ru:3306/host1656712_testdb?serverTimezone=Europe/Moscow";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            connection = DriverManager.getConnection(url, "host1656712_test", "D0qZYJcm");
     */

    public static Connection getConnection(){
        Connection connection = null;
        try {
            String url = "jdbc:hsqldb:file:db";
            JDBCDriver driver = new org.hsqldb.jdbc.JDBCDriver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(url);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }
}
