package com.dormitory.app.helpful;

import com.dormitory.app.database.SetConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MarketNewsCreator implements Comparable<MarketNewsCreator>{
    private String title;
    private String text_mark;
    private String author;

    private String date_mark;
    private int rating;
    private int tag_id_mark;
    private String contact_info;

    private int mark_id;

    public int getMark_id() {
        return mark_id;
    }

    public void setMark_id(int mark_id) {
        this.mark_id = mark_id;
    }

    public String getText_mark() {
        return text_mark;
    }

    public void setText_mark(String text_mark) {
        this.text_mark = text_mark;
    }

    public String getDate_mark() {
        return date_mark;
    }

    public void setDate_mark(String date_mark) {
        this.date_mark = date_mark;
    }

    public int getTag_id_mark() {
        return tag_id_mark;
    }

    public void setTag_id_mark(int tag_id_mark) {
        this.tag_id_mark = tag_id_mark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(int author_id) {
        this.author = null;
        try {
            Connection connection = SetConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT login FROM User WHERE user_id = ?");
            statement.setInt(1, author_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                this.author = resultSet.getString("login");
            }
            statement.close();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    @Override
    public int compareTo(MarketNewsCreator o) {
        if (o.getRating() < this.getRating()){
            return -1;
        }
        else return 1;
    }
}
