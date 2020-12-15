package com.dormitory.app.helpful;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class CommonNewsCreator implements Comparable<CommonNewsCreator> {
    private String text;
    private String header = "";
    private String tagName = "";
    private String strongText = "";
    private String usualText = "";
    private int id = 0;
    public CommonNewsCreator(int id){
    }

    public String getText(){
        String toInsert = "";
        if (tagName.equals("Важно")){
            toInsert += "                        &#160; <span class=\"badge rounded-pill bg-danger\">" + tagName + "</span>\n";
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(dateFormat.parse(strongText));
            cal1.add(Calendar.DATE, 2);

            Calendar calNow = Calendar.getInstance();
            calNow.setTime(dateFormat.parse(LocalDate.now().toString()));

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(strongText));

            if (calendar.after(calNow)){
                toInsert += " &#160; <span class=\"badge rounded-pill bg-info text-dark\">" + "Запланировано" + "</span>";
            }
            else {
                if (calNow.before(cal1)) {
                    toInsert += "                      &#160;  <span class=\"badge rounded-pill bg-success\">" + "Новое" + "</span>\n";
                }
            }
        }
        catch (Exception e){
            System.out.println("Неправильный парсинг");
        }
        this.text = "<div class=\"accordion-item\">\n" +
                "                <h2 class=\"accordion-header\" id=\"heading" + String.valueOf(id) + "\">\n" +
                "                    <button class=\"accordion-button\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#collapse" + String.valueOf(id) + "\" aria-expanded=\"false\" aria-controls=\"collapse" + String.valueOf(id) + "\">\n" +
                "                        <span class=\"toTheLeft\">" + header + "</span>\n" +
                toInsert +
                "                    </button>\n" +
                "                </h2>\n" +
                "                <div id=\"collapse" + String.valueOf(id) + "\" class=\"accordion-collapse collapse\" aria-labelledby=\"heading" + String.valueOf(id) + "\" data-bs-parent=\"#accordionExample\">\n" +
                "                    <div class=\"accordion-body\">\n" +
                "                        <strong>" + strongText + "</strong> <br>" + usualText + "\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>";
        return text;
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
