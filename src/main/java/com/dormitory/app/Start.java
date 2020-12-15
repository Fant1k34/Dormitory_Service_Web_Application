package com.dormitory.app;

import com.dormitory.app.database.Business;
import com.dormitory.app.helpful.CommonNewsCreator;
import com.dormitory.app.helpful.LoginAndPassword;
import com.dormitory.app.helpful.PersonInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;


@Controller
public class Start {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexG(Model model, HttpSession session) {
        // Если пользователь не вошёл (нет сессии)
        if (session.getAttribute("successLogin") == null){
            model.addAttribute("message", "Требуется авторизация");
            return "login";
        }

        boolean result = (boolean) session.getAttribute("successLogin");
        if (!result){
            // Если пароль или логин введены неверно
            model.addAttribute("message", "Неверно введены логин и пароль");
            return "login";
        }
        // Если у пользователя вверно введены логин и пароль
        String log = (String) session.getAttribute("login");
        model.addAttribute("login", log);

        // TODO Тут надо проверить, какая у него группа безопасности


        // Отображаем все новости

        try {
            if ((int) session.getAttribute("group_id") != 0) {
                Business businessClass = new Business();
                ArrayList<CommonNewsCreator> news = businessClass.putAllCommonNewsToCommonNewsCreator();
                Collections.sort(news);
                String toShow = "";

                // Если не администратор, то тогда мы не видим будующие новости
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                // calNow - день сейчас
                Calendar calNow = Calendar.getInstance();
                calNow.setTime(dateFormat.parse(LocalDate.now().toString()));

                for (CommonNewsCreator el : news) {
                    // calendar - день новости
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dateFormat.parse(el.getStrongText()));
                    // Если calendar после calNow -> новость не отображается
                    if (calendar.after(calNow)) {
                        continue;
                    }
                    toShow += el.getText() + "\n";
                }
                // Передаём строку из новостей в newsText
                model.addAttribute("newsText", toShow);
                return "start";
            }
        }
        catch (Exception e){
            System.out.println("Что-то не так с датами: их форматом. Будут отображены все новости");
        }

        // Если администратор, то всё как обычно:
        Business businessClass = new Business();
        ArrayList<CommonNewsCreator> news = businessClass.putAllCommonNewsToCommonNewsCreator();
        Collections.sort(news);
        String toShow = "";

        for (CommonNewsCreator el : news) {
            toShow += el.getText() + "\n";
        }
        // Передаём строку из новостей в newsText
        model.addAttribute("newsText", toShow);
        return "start";

    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    // Это метод POST, который принимает @ModelAttribute произвольного класса, который заполняется
    // автоматически в форме, html код которой содержит поля этого класса
    // Все такие классы лежат в пакете helpful
    public String indexP(Model model, HttpSession session, @ModelAttribute LoginAndPassword loginAndPassword) throws IOException {
        boolean successLogin = Business.checkLoginAndPassword(loginAndPassword.getLogin(), loginAndPassword.getPassw());
        session.setMaxInactiveInterval(360);
        session.setAttribute("successLogin", successLogin);
        session.setAttribute("login", loginAndPassword.getLogin());

        PersonInfo person = Business.getPersonByLogin(loginAndPassword.getLogin());

        session.setAttribute("group_id", person.getGroup_id());
        return "redirect:/";
    }

}