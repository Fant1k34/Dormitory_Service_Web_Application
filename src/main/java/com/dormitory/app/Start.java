package com.dormitory.app;

import com.dormitory.app.database.Business;
import com.dormitory.app.helpful.CommonNewsCreator;
import com.dormitory.app.helpful.LoginAndPassword;
import com.dormitory.app.helpful.PersonInfo;
import com.dormitory.app.helpful.Tag;
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


        // Отображем блоки


        // Отображаем все новости

        int group_id = (int) session.getAttribute("group_id");
        addNewsCommonToModel(group_id, model);
        // Определяем новости для каждой группы пользователей
        // String toShow = showNews(group_id);
        // Передаём строку из новостей в newsText
        // model.addAttribute("newsText", toShow);


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


    public static void addNewsCommonToModel(int group_id, Model model){
        ArrayList<CommonNewsCreator> news = Business.putAllCommonNewsToCommonNewsCreator();
        news.stream().forEach(i -> System.out.println(i.tags));
        Collections.sort(news);
        ArrayList<CommonNewsCreator> filteredNews = new ArrayList<>();
        try {
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
                // Если администратор, то всё как обычно:
                if (calendar.after(calNow) && group_id != 0) {
                    continue;
                }

                // Если новость надо показывать - значит, добавляем идентификаторы

                SimpleDateFormat dateFormatNew = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal1New = Calendar.getInstance();
                cal1New.setTime(dateFormatNew.parse(el.getStrongText()));
                cal1New.add(Calendar.DATE, 2);

                Calendar calNowNew = Calendar.getInstance();
                calNowNew.setTime(dateFormatNew.parse(LocalDate.now().toString()));

                Calendar calendarNew = Calendar.getInstance();
                calendarNew.setTime(dateFormatNew.parse(el.getStrongText()));


                System.out.println("Ждём!!!");
                if (calendarNew.after(calNowNew)){
                    el.tags.add(new Tag("Запланировано"));
                }
                else {
                    if (calNowNew.before(cal1New)) {
                        el.tags.add(new Tag("Новое"));
                    }
                }

                filteredNews.add(el);
            }
            // Возвращаем html - вставку
            model.addAttribute("commonNews", filteredNews);
            filteredNews.stream().map(i -> i.getTags()).forEach(System.out::println);
            return;
        }
        catch (Exception e){
            System.out.println("Ошибка в парсинге дат! В новости указана неправильная дата");
        }

        try {
            for (CommonNewsCreator el : news) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateFormat.parse(el.getStrongText()));

                SimpleDateFormat dateFormatNew = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal1New = Calendar.getInstance();
                cal1New.setTime(dateFormatNew.parse(el.getStrongText()));
                cal1New.add(Calendar.DATE, 2);

                Calendar calNowNew = Calendar.getInstance();
                calNowNew.setTime(dateFormatNew.parse(LocalDate.now().toString()));

                Calendar calendarNew = Calendar.getInstance();
                calendarNew.setTime(dateFormatNew.parse(el.getStrongText()));

                if (calendarNew.after(calNowNew)) {
                    el.tags.add(new Tag("Запланировано"));
                } else {
                    if (calNowNew.before(cal1New)) {
                        el.tags.add(new Tag("Новое"));
                    }
                }
            }
        }
        catch (Exception e){}

        filteredNews.addAll(news);
        model.addAttribute("commonNews", filteredNews);
        filteredNews.stream().map(i -> i.getTags()).forEach(System.out::println);
    }


}