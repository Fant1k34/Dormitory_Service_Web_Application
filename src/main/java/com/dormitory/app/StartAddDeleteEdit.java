package com.dormitory.app;

import com.dormitory.app.database.Business;
import com.dormitory.app.database.InsertData;
import com.dormitory.app.database.ServiceDatabase;
import com.dormitory.app.helpful.CommonNewsCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class StartAddDeleteEdit {
    @RequestMapping(value = "/addcommon", method = RequestMethod.GET)
    public String addToCommon(HttpSession session){
        session.setAttribute("toShow", "Adding");
        return "redirect:/";
    }

    @RequestMapping(value = "/addcommon", method = RequestMethod.POST)
    public String addToCommonPost(@ModelAttribute("addingAtt") CommonNewsCreator commonNewsCreator, HttpSession session){
        String title = commonNewsCreator.getHeader();
        String text = commonNewsCreator.getUsualText();
        int author_id = Business.getUserIdByLogin((String) session.getAttribute("login"));
        LocalDate date = LocalDate.parse(commonNewsCreator.getStrongText());
        int tag = Business.getTagIdByName(commonNewsCreator.getTagName());

        InsertData.insertToCommonNews(title, text, author_id, date, tag);
        session.setAttribute("toShow", "Adding");
        return "redirect:/";
    }

    @RequestMapping("/editсommon")
    public String editCommon(HttpSession session){
        session.setAttribute("toShow", "Editing");
        return "redirect:/";
    }

    @RequestMapping("/showсommon")
    public String showCommon(HttpSession session){
        session.setAttribute("toShow", "News");
        return "redirect:/";
    }
}
