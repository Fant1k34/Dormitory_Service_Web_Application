package com.dormitory.app.helpful;

public class Tag {
    public String TagText = "";
    public String TagTextColor = "";
    public String TagColor = "";

    public Tag(String tagText) {
        TagText = tagText;
        if (tagText.equals("Важно")){
            TagColor = "bg-danger";
            TagTextColor = "";
        }
        if (tagText.equals("Новое")) {
            TagColor = "bg-success";
            TagTextColor = "";
        }
        if (tagText.equals("Запланировано")){
            TagColor = "bg-info";
            TagTextColor = "text-dark";
        }
    }

    public String getTagText() {
        return TagText;
    }

    public void setTagText(String tagText) {
        TagText = tagText;
    }

    public String getTagTextColor() {
        return TagTextColor;
    }

    public void setTagTextColor(String tagTextColor) {
        TagTextColor = tagTextColor;
    }

    public String getTagColor() {
        return TagColor;
    }

    public void setTagColor(String tagColor) {
        TagColor = tagColor;
    }
}
