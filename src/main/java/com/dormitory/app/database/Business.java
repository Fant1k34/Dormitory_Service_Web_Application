package com.dormitory.app.database;

import com.dormitory.app.SortFlagForMarket;
import com.dormitory.app.helpful.CommonNewsCreator;
import com.dormitory.app.helpful.MarketNewsCreator;
import com.dormitory.app.helpful.PersonInfo;
import com.dormitory.app.helpful.PictureMarket;
import org.springframework.web.multipart.MultipartFile;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

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

    public static ArrayList<MarketNewsCreator> putAllNewsToMarketNewsCreator(SortFlagForMarket sortFlagForMarket, String login){
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
                marketNewsCreator.setMark_id(String.valueOf(resultSet.getInt("new_mar_id")));

                marketNewsCreator.setSortFlagForMarket(sortFlagForMarket);

                PreparedStatement statement1 = connection.prepareStatement("SELECT COUNT(*) FROM Liked WHERE new_mar_id = ?");
                statement1.setInt(1, Integer.parseInt(marketNewsCreator.getMark_id()));
                ResultSet resultSet1 = statement1.executeQuery();
                resultSet1.next();
                marketNewsCreator.setRating(resultSet1.getInt(1));

                PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM Liked AS L WHERE new_mar_id = ? AND L.user_id = (SELECT user_id from User WHERE login = ?)");
                statement2.setInt(1, Integer.parseInt(marketNewsCreator.getMark_id()));
                statement2.setString(2, login);
                ResultSet resultSet2 = statement2.executeQuery();
                int counterLiked = 0;
                while (resultSet2.next()){
                    counterLiked++;
                }
                if (counterLiked == 1){
                    marketNewsCreator.setLiked(true);
                }
                else {
                    marketNewsCreator.setLiked(false);
                }

                allMarketNews.add(marketNewsCreator);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return allMarketNews;
    }

    public static ArrayList<PictureMarket> getAllPicturesFromDBById(int id){
        ArrayList<PictureMarket> allPictures = new ArrayList<>();

        try{
            Connection connection = SetConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM PictureMarket WHERE new_mar_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                byte[] picturesBytes = resultSet.getBytes("picture");
                int idMar = resultSet.getInt("new_mar_id");
                int idNew = resultSet.getInt("picture_id");

                PictureMarket pictureMarket = new PictureMarket();
                pictureMarket.setBytes(picturesBytes);
                pictureMarket.setIdMark(idMar);
                pictureMarket.setIdPicture(idNew);

                allPictures.add(pictureMarket);
            }
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Что-то не так с работой BLOB");
        }

        return allPictures;
    }


    public static PictureMarket getPictureFromDBByIdPicture(int id){
        ArrayList<PictureMarket> allPictures = new ArrayList<>();

        try{
            Connection connection = SetConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM PictureMarket WHERE picture_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                byte[] picturesBytes = resultSet.getBytes("picture");
                int idMar = resultSet.getInt("new_mar_id");
                int idNew = resultSet.getInt("picture_id");

                PictureMarket pictureMarket = new PictureMarket();
                pictureMarket.setBytes(picturesBytes);
                pictureMarket.setIdMark(idMar);
                pictureMarket.setIdMark(idNew);

                allPictures.add(pictureMarket);
            }
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Что-то не так с работой BLOB");
        }

        return allPictures.get(0);
    }


    public static int getUserIdByLogin(String login){
        int id = 2;
        try{
            Connection connection = SetConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT user_id FROM User WHERE login = ?");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            id = resultSet.getInt(1);
            statement.close();
            connection.close();
        }
        catch (Exception e){
        }
        return id;
    }


    public static boolean deleteByMarketNewsID(int marketId, int group, String login){
        boolean flag = true;
        if (group == 0) {
            try {
                Connection connection = SetConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM MarketNews WHERE new_mar_id = ?");
                statement.setInt(1, marketId);
                statement.execute();
                statement.close();
                connection.close();
            } catch (Exception e) {
                flag = false;
            }
        }
        else {
            try {
                Connection connection = SetConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM MarketNews WHERE new_mar_id = ? AND (SELECT user_id FROM User WHERE login = ?) = author_id");
                statement.setInt(1, marketId);
                statement.setString(2, login);
                statement.execute();
                statement.close();
                connection.close();
            } catch (Exception e) {
                flag = false;
            }
        }
        return flag;
    }



    public static ArrayList<String> showAuthorInfoByMarkId(int MarketId){
        String contact_info = "";
        String block_id = "";
        try{
            Connection connection = SetConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT contact_info, block_id FROM MarketNews JOIN User ON user_id = author_id WHERE new_mar_id = ?");
            statement.setInt(1, MarketId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            contact_info = resultSet.getString(1);
            block_id = resultSet.getString(2);
            statement.close();
            connection.close();
        }
        catch (Exception e){
        }
        return new ArrayList<>(Arrays.asList(contact_info, block_id));
    }

    public static String getAuthorByMarketNews(int id){
        String answer = "";
        try{
            Connection connection = SetConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT login FROM User WHERE user_id = (SELECT author_id FROM MarketNews WHERE new_mar_id = ?)");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            answer = resultSet.getString(1);
        }
        catch (Exception e){
        }
        return answer;
    }

    public static int getTagIdByName(String name){
        int answer = -10;
        try{
            Connection connection = SetConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT tag_id FROM Tag WHERE tag_text = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            answer = resultSet.getInt(1);
            statement.close();
            connection.close();
        }
        catch (Exception e){
        }
        return answer;
    }

}
