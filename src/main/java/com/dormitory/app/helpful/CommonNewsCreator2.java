package com.dormitory.app.helpful;

import java.sql.Date;
import java.util.ArrayList;

public class CommonNewsCreator2 implements Comparable<CommonNewsCreator2> {
    private String header2 = "";

    public int getIdCommon() {
        return idCommon;
    }

    public void setIdCommon(int idCommon) {
        this.idCommon = idCommon;
    }

    private int idCommon;

    public String getHeader3() {
        return header3;
    }

    public void setHeader3(String header3) {
        this.header3 = header3;
    }

    private String header3 = "";
    private String tagName2 = "";
    private String strongText2 = "";
    private String usualText2 = "";

    private String Fid = "";
    public ArrayList<Tag> tags = new ArrayList<>();

    public String getFid() {
        return Fid;
    }

    public void setFid(String fid) {
        Fid = "heading" + fid;
    }

    private int id = 0;

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader2() {
        return header2;
    }

    public void setHeader2(String header2) {
        this.header2 = header2;
    }

    public String getTagName2() {
        return tagName2;
    }

    public void setTagName2(String tagName2) {
        this.tagName2 = tagName2;
        tags.add(new Tag(tagName2));
    }

    public String getStrongText2() {
        return strongText2;
    }

    public void setStrongText2(String strongText2) {
        this.strongText2 = strongText2;
    }

    public String getUsualText2() {
        return usualText2;
    }

    public void setUsualText2(String usualText2) {
        this.usualText2 = usualText2;
    }

    @Override
    public int compareTo(CommonNewsCreator2 o) {
        if (Date.valueOf(this.getStrongText2()).after(Date.valueOf(o.getStrongText2()))){
            return -1;
        }
        else return 1;
    }
}
