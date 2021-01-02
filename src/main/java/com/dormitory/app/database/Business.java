package com.dormitory.app.database;

import com.dormitory.app.helpful.CommonNewsCreator;
import com.dormitory.app.helpful.MarketNewsCreator;
import com.dormitory.app.helpful.PersonInfo;

import java.sql.*;
import java.util.ArrayList;

public class Business {
    public static boolean checkLoginAndPassword(String loginIn, String passwordIn){
        try {
            Connection connection = SetConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT passw FROM User WHERE login = ?");
            statement.setString(1, loginIn);
            ResultSet resultSet = statement.executeQuery();
            int k = 0;
            String passwordOut = null;
            while (resultSet.next()){
                passwordOut = resultSet.getString(1);
                k++;
            }
            if (k == 0) return false; // Нет такого пользователя в базе данных
            if (k > 1){
                throw new Exception("Ошибка в базе данных! Нет уникальности логинов");
            }
            if (!passwordIn.equals(passwordOut)){
                return false;
            }
            return true;
        }
        catch (Exception e){
        }
        throw new RuntimeException("Косяк с кодом");
    }


    public static ArrayList<CommonNewsCreator> putAllCommonNewsToCommonNewsCreator(){
        ArrayList<CommonNewsCreator> answer = new ArrayList<>();

        try {
            Connection connection = SetConnection.getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT CN.*, T.tag_text FROM CommonNews AS CN JOIN Tag AS T ON CN.tag_id = T.tag_id");

            while (resultSet.next()){
                int id = resultSet.getInt("new_com_id");
                String title = resultSet.getString("title");
                String text = resultSet.getString("text");
                Date date = resultSet.getDate("date");
                String tagText = resultSet.getString("tag_text");

                CommonNewsCreator news = new CommonNewsCreator();
                news.setId(id);
                news.setHeader(title);
                news.setTagName(tagText);
                news.setStrongText(date.toString());
                news.setUsualText(text);
                answer.add(news);
            }
            statement.close();
            connection.close();
        }
        catch (Exception e){}
        return answer;
    }

    public static ArrayList<PersonInfo> putAllInfoToPeopleInfo(){
        ArrayList<PersonInfo> people = new ArrayList<>();
        try {
            Connection connection = SetConnection.getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM User");
            while (resultSet.next()){
                int id = resultSet.getInt("user_id");
                String login = resultSet.getString("login");
                int group_id = resultSet.getInt("group_id");
                Date group_date = resultSet.getDate("group_date");
                int group_duration = resultSet.getInt("group_duration");
                String user_name = resultSet.getString("user_name");
                String user_surname = resultSet.getString("user_surname");
                int isu_number = resultSet.getInt("isu_number");
                String block_id = resultSet.getString("block_id");

                PersonInfo person = new PersonInfo();
                person.setUser_id(id);
                person.setLogin(login);
                person.setGroup_id(group_id);
                person.setGroup_date(group_date);
                person.setGroup_duration(group_duration);
                person.setUser_name(user_name);
                person.setUser_surname(user_surname);
                person.setIsu_number(isu_number);
                person.setBlock_id(block_id);

                people.add(person);
            }
        }
        catch (Exception e){}
        return people;
    }

    public static PersonInfo getPersonByLogin(String login){
        PersonInfo person = new PersonInfo();
        try {
            Connection connection = SetConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE login = ?");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("user_id");
                int group_id = resultSet.getInt("group_id");
                Date group_date = resultSet.getDate("group_date");
                int group_duration = resultSet.getInt("group_duration");
                String user_name = resultSet.getString("user_name");
                String user_surname = resultSet.getString("user_surname");
                int isu_number = resultSet.getInt("isu_number");
                String block_id = resultSet.getString("block_id");

                person.setUser_id(id);
                person.setLogin(login);
                person.setGroup_id(group_id);
                person.setGroup_date(group_date);
                person.setGroup_duration(group_duration);
                person.setUser_name(user_name);
                person.setUser_surname(user_surname);
                person.setIsu_number(isu_number);
                person.setBlock_id(block_id);
            }
        }
        catch (Exception e){}
        return person;
    }

    public static ArrayList<MarketNewsCreator> putAllNewsToMarketNewsCreator(){
        ArrayList<MarketNewsCreator> allMarketNews = new ArrayList<>();
        try {
            Connection connection = SetConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MarketNews");
            while (resultSet.next()){
                MarketNewsCreator marketNewsCreator = new MarketNewsCreator();

                marketNewsCreator.setTitle(resultSet.getString("title"));
                marketNewsCreator.setText_mark(resultSet.getString("text"));
                marketNewsCreator.setAuthor(resultSet.getInt("author_id"));
                marketNewsCreator.setDate_mark(String.valueOf(resultSet.getDate("date")));
                marketNewsCreator.setContact_info(resultSet.getString("contact_info"));
                marketNewsCreator.setRating(resultSet.getInt("rating"));

                allMarketNews.add(marketNewsCreator);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return allMarketNews;
    }
}
