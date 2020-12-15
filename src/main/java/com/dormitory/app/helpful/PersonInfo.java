package com.dormitory.app.helpful;

import java.sql.Date;

public class PersonInfo {
    private int user_id;
    private String login;
    private int group_id;
    private Date group_date;
    private int group_duration;
    private String user_name;
    private String user_surname;
    private int isu_number;
    private String block_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public Date getGroup_date() {
        return group_date;
    }

    public void setGroup_date(Date group_date) {
        this.group_date = group_date;
    }

    public int getGroup_duration() {
        return group_duration;
    }

    public void setGroup_duration(int group_duration) {
        this.group_duration = group_duration;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_surname() {
        return user_surname;
    }

    public void setUser_surname(String user_surname) {
        this.user_surname = user_surname;
    }

    public int getIsu_number() {
        return isu_number;
    }

    public void setIsu_number(int isu_number) {
        this.isu_number = isu_number;
    }

    public String getBlock_id() {
        return block_id;
    }

    public void setBlock_id(String block_id) {
        this.block_id = block_id;
    }
}
