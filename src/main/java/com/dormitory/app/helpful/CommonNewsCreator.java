package com.dormitory.app.helpful;

import java.sql.Date;
import java.util.ArrayList;

public class CommonNewsCreator implements Comparable<CommonNewsCreator> {
    private String header = "";
    private String tagName = "";
    private String strongText = "";
    private String usualText = "";

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

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
        tags.add(new Tag(tagName));
    }

    public String getStrongText() {
        return strongText;
    }

    public void setStrongText(String strongText) {
        this.strongText = strongText;
    }

    public String getUsualText() {
        return usualText;
    }

    public void setUsualText(String usualText) {
        this.usualText = usualText;
    }

    @Override
    public int compareTo(CommonNewsCreator o) {
        if (Date.valueOf(this.getStrongText()).after(Date.valueOf(o.getStrongText()))){
            return -1;
        }
        else return 1;
    }
}
