package com.dormitory.app.database;

import org.hsqldb.jdbc.JDBCDriver;

import java.sql.*;
import java.time.LocalDate;

public class InsertData {
    public static void insertToCommonNews(){
        try{
            Connection connection = SetConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO CommonNews (title, text, author_id, date, tag_id) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1,"Начало семестра");
            statement.setString(2,"Весенний семестр начинается завтра!");
            statement.setInt(3, 1);
            statement.setDate(4, Date.valueOf(LocalDate.of(2021, 2, 6)));
            statement.setInt(5, 1); // тег
            statement.execute();
            statement.close();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public static void insertToTag(int id, String tagName){
        try{
            Connection connection = SetConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Tag VALUES (?, ?)");
            statement.setInt(1,id);
            statement.setString(2,tagName);
            statement.execute();
            statement.close();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public static void insertToMarket(String title, String text, int author_id, LocalDate date, String contact_info, int rating, int tag_id){
        try {
            Connection connection = SetConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO MarketNews(title, text, author_id, date, contact_info, rating, tag_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, title);
            statement.setString(2, text);
            statement.setInt(3, author_id);
            statement.setDate(4, Date.valueOf(date));
            statement.setString(5, contact_info);
            statement.setInt(6, rating);
            statement.setInt(7, tag_id);
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
            Connection connection = SetConnection.getConnection();

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
        // InsertData.insertToCommonNews();
        // InsertData.insertToTag(4, "Услуга");
        // InsertData.insertToMarket("Печатаю лабы", "Две лабы печатаю - третья в подарок* <br> * - Третья лабораторная - самая короткая", 1, LocalDate.of(2020, 12, 6), "803б", 14, 4);
        InsertData.showCommon();
        System.out.println("+++");
    }
}
