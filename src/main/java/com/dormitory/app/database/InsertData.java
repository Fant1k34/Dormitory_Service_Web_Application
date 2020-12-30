package com.dormitory.app.database;

import org.hsqldb.jdbc.JDBCDriver;

import java.sql.*;
import java.time.LocalDate;

public class InsertData {
    static JDBCDriver driver = ServiceDatabase.driver;
    static String url = ServiceDatabase.url;

    public static void insertToCommonNews(){
        try{
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO CommonNews (title, text, author_id, date, tag_id) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1,"Что-то в будущем");
            statement.setString(2,"И ещё важно!");
            statement.setInt(3, 1);
            statement.setDate(4, Date.valueOf(LocalDate.of(2020, 12, 30)));
            statement.setInt(5, 1);
            statement.execute();
            statement.close();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public static void insertToTag(){
        try{
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Tag VALUES (?, ?)");
            statement.setInt(1,2);
            statement.setString(2,"Стандартное");
            statement.execute();
            statement.close();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }


    public static void showCommon(){
        try {
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(url);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CommonNews");

            while (resultSet.next()) {
                int id = resultSet.getInt("new_com_id");
                String title = resultSet.getString("title");
                String text = resultSet.getString("text");
                Date date = resultSet.getDate("date");
                System.out.println(id + " - " +title + " - " + text + " - " + date);
            }

            resultSet = statement.executeQuery("SELECT * FROM Tag");
            while (resultSet.next()) {
                int title = resultSet.getInt("tag_id");
                String text = resultSet.getString("tag_text");
                System.out.println(title + " - " + text);
            }

            statement.close();
            connection.close();
        }
        catch (Exception e){}
    }



    public static void main(String[] args) {
        InsertData.insertToCommonNews();
        // InsertData.insertToTag();
        InsertData.showCommon();
        System.out.println("+++");
    }
}
