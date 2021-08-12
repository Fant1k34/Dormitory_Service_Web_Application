package com.dormitory.app.database;

import com.dormitory.app.helpful.CommonNewsCreator;
import com.dormitory.app.helpful.MarketNewsCreator;
import org.hsqldb.jdbc.JDBCDriver;

import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class InsertData {
    public static void insertToCommonNews(String title, String text, int author_id, LocalDate date, int tag_id){
        try{
            Connection connection = SetConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO CommonNews (title, text, author_id, date, tag_id) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1,title);
            statement.setString(2,text);
            statement.setInt(3, author_id);
            statement.setDate(4, Date.valueOf(date));
            statement.setInt(5, tag_id); // тег
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

    public static String insertToMarket(MarketNewsCreator marketNewsCreator){
        String title = marketNewsCreator.getTitle();
        String text = marketNewsCreator.getText_mark();
        int author_id = marketNewsCreator.getAuthorId();
        LocalDate date = LocalDate.parse(marketNewsCreator.getDate_mark());
        String contact_info = marketNewsCreator.getContact_info();
        int rating = marketNewsCreator.getRating();
        int tag_id = marketNewsCreator.getTag_id_mark();
        return insertToMarket(title, text, author_id, date, contact_info, rating, tag_id);
    }

    public static String insertToMarket(String title, String text, int author_id, LocalDate date, String contact_info, int rating, int tag_id){
        int id = -10;

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

            PreparedStatement statement2 = connection.prepareStatement("SELECT new_mar_id FROM MarketNews WHERE title = ? AND text = ?");
            statement2.setString(1, title);
            statement2.setString(2, text);
            ResultSet resultSet = statement2.executeQuery();
            resultSet.next();
            id = resultSet.getInt(1);

            statement.close();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return String.valueOf(id);
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


    public static String putPictureToDBRelatedToNewById(int id, byte[] bytes){
        int k = (int) (System.currentTimeMillis() / 1000);
        try {
            Connection connection = SetConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO PictureMarket(picture_id, new_mar_id, picture) VALUES(?, ?, ?)");
            statement.setInt(1, k);
            statement.setInt(2, id);
            statement.setBytes(3, bytes);
            statement.execute();
            statement.close();
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Ошибка в вставке");
        }
        return String.valueOf(k);
    }

    public static String putPictureToDBRelatedToNewById(int k, int id, byte[] bytes){
        try {
            Connection connection = SetConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO PictureMarket(picture_id, new_mar_id, picture) VALUES(?, ?, ?)");
            statement.setInt(1, k);
            statement.setInt(2, id);
            statement.setBytes(3, bytes);
            statement.execute();
            statement.close();
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Ошибка в вставке");
        }
        return String.valueOf(k);
    }



    public static void main(String[] args) {
        ServiceDatabase.create("User");
        ServiceDatabase.create("Permission");
        ServiceDatabase.create("CommonNews");
        ServiceDatabase.create("MarketNews");
        ServiceDatabase.create("PinnedMessage");
        ServiceDatabase.create("PictureCommon");
        ServiceDatabase.create("Color");
        ServiceDatabase.create("Liked");

        Calendar calendar = new GregorianCalendar(2021, 7 , 11);

        ServiceDatabase.addToUser("Fant1k43", "123456", 0, calendar.getTime(), 99999999, "Никита", "Дукин", 284744, "803б");
        // InsertData.insertToCommonNews();
        // InsertData.insertToTag(4, "Услуга");
        // InsertData.insertToMarket("Печатаю лабы", "Две лабы печатаю - третья в подарок* <br> * - Третья лабораторная - самая короткая", 1, LocalDate.of(2020, 12, 6), "803б", 14, 4);
        InsertData.showCommon();
        System.out.println("+++");
    }
}
