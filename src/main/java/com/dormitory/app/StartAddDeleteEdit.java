package com.dormitory.app;

import com.dormitory.app.database.Business;
import com.dormitory.app.database.InsertData;
import com.dormitory.app.database.ServiceDatabase;
import com.dormitory.app.helpful.CommonNewsCreator;
import com.dormitory.app.helpful.CommonNewsCreator2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class StartAddDeleteEdit {
    @RequestMapping(value = "/addcommon", method = RequestMethod.GET)
    public String addToCommon(HttpSession session){
        if ((int) session.getAttribute("group_id") == 0) {
            session.setAttribute("toShow", "Adding");
            return "redirect:/";
        }
        session.setAttribute("toShow", "News");
        return "redirect:/";
    }

    @RequestMapping(value = "/addcommon", method = RequestMethod.POST)
    public String addToCommonPost(@ModelAttribute("addingAtt") CommonNewsCreator commonNewsCreator, HttpSession session){
        if ((int) session.getAttribute("group_id") != 0) {
            session.setAttribute("toShow", "News");
            return "redirect:/";
        }

        String title = commonNewsCreator.getHeader();
        String text = commonNewsCreator.getUsualText();
        int author_id = Business.getUserIdByLogin((String) session.getAttribute("login"));
        LocalDate date;
        try {
            date = LocalDate.parse(commonNewsCreator.getStrongText());
        }
        catch (Exception e){
            date = LocalDate.now();
        }
        int tag = Business.getTagIdByName(commonNewsCreator.getTagName());

        if (author_id < 0){
            session.setAttribute("exception", "Сессия пользователя не найдена. Перезайдите в сервис");
            return "redirect:/";
        }
        if (tag < 0){
            session.setAttribute("exception", "Тег указан неверно или не существует! Пожалуйста, не меняйте html-код");
            return "redirect:/";
            // вернуть ошибку
        }
        InsertData.insertToCommonNews(title, text, author_id, date, tag);
        session.setAttribute("toShow", "News");
        return "redirect:/";
    }

    @RequestMapping(value = "/editcommon", method = RequestMethod.POST)
    public String editCommonPost(HttpSession session, @ModelAttribute("editingAtt") CommonNewsCreator2 editingAtt){
        if ((int) session.getAttribute("group_id") != 0) {
            session.setAttribute("toShow", "News");
            return "redirect:/";
        }
        session.setAttribute("startValues", editingAtt.getHeader2());
        session.setAttribute("toShow", "Editing");
        return "redirect:/";
    }

    @RequestMapping(value = "/editcommon", method = RequestMethod.GET)
    public String editCommon(HttpSession session){
        if ((int) session.getAttribute("group_id") != 0) {
            session.setAttribute("toShow", "News");
            return "redirect:/";
        }
        session.setAttribute("toShow", "Editing");
        return "redirect:/";
    }

    @RequestMapping("/showсommon")
    public String showCommon(HttpSession session){
        session.setAttribute("toShow", "News");
        return "redirect:/";
    }

    @RequestMapping(value = "/changecommon", method = RequestMethod.POST)
    public String changeCommon(@ModelAttribute("editingAtt") CommonNewsCreator2 editingAtt, HttpSession session){
        if ((int) session.getAttribute("group_id") != 0) {
            session.setAttribute("toShow", "News");
            return "redirect:/";
        }
        try{
            session.getAttribute("chosen");
        }
        catch (Exception e){
            System.out.println("ЧТО-ТО НЕ ТАК С CHOSEN!!!");
            session.setAttribute("exception", "Не так выбран параметр!");
        }
        if (Business.updateDBCommonNewByTitle((String) session.getAttribute("chosen"), editingAtt.getHeader3(), editingAtt.getUsualText2(), editingAtt.getStrongText2(), Business.getTagIdByName(editingAtt.getTagName2()))) {
            session.setAttribute("toShow", "News");
            session.setAttribute("exception", null);
            return "redirect:/";
        }
        System.out.println("СТРАННЫЕ ДЕЛА!!!!!!!!!!!!!!!!!!!");
        session.setAttribute("exception", "Не верно введены данные!");
        return "redirect:/";
    }

    @RequestMapping("/deletecommon/{title}")
    public String delCommon(@PathVariable("title") String title, HttpSession session){
        if ((int) session.getAttribute("group_id") != 0) {
            session.setAttribute("toShow", "News");
            return "redirect:/";
        }
        System.out.println(title);
        if (Business.deleteCommonNewsByTitle(title)){
            return "redirect:/";
        }
        session.setAttribute("exception", "Ошибка в удалении");
        return "redirect:/";
    }
}
