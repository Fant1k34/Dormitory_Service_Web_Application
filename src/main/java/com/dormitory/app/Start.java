package com.dormitory.app;

import com.dormitory.app.database.Business;
import com.dormitory.app.database.ServiceDatabase;
import com.dormitory.app.helpful.*;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;


@Controller
public class Start {
    private MessageDigest digest;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexG(Model model, HttpSession session) throws ParseException {
        // Если пользователь не вошёл (нет сессии)
        if (session.getAttribute("login") == null){
            session.setAttribute("isLikeButtonActive", false);
            session.setAttribute("search", "");
            session.setAttribute("toShow", "News");

            // model.addAttribute("search", new FindProperly(""));
            model.addAttribute("toLogIn", new LoginAndPassword());
            return "loginnew";
        }

        boolean result = (boolean) session.getAttribute("successLogin");
        if (!result){
            // Если пароль или логин введены неверно
            // model.addAttribute("message", "Неверно введены логин и пароль");
            return "loginnew";
        }
        // Если у пользователя вверно введены логин и пароль
        String log = (String) session.getAttribute("login");
        model.addAttribute("login", log);

        if ((int) session.getAttribute("group_id") == 1){

            SimpleDateFormat dateFormatNew = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal1New = Calendar.getInstance();
            cal1New.setTime(dateFormatNew.parse(Business.getGroupStartDateByLogin((String) session.getAttribute("login")).toString()));
            cal1New.add(Calendar.DATE, Business.getGroupDuration((String) session.getAttribute("login")));

            Calendar calNowNew = Calendar.getInstance();
            calNowNew.setTime(dateFormatNew.parse(LocalDate.now().toString()));

            if (!calNowNew.before(cal1New)) {
                Business.changeGroupByLogin((String) session.getAttribute("login"));
                session.invalidate();
                return "redirect:/";
            }
        }
        // Отображем блоки


        // Отображаем все новости

        int group_id = (int) session.getAttribute("group_id");
        addNewsCommonToModel(group_id, model);

        if (((String) session.getAttribute("toShow")) == null){
            return "start";
        }

        model.addAttribute("toShow", (String) session.getAttribute("toShow"));

        if (((String) session.getAttribute("toShow")).equals("News")){
            return "start";
        }
        if (((String) session.getAttribute("toShow")).equals("Adding")){
            model.addAttribute("addingAtt", new CommonNewsCreator());
        }
        if (((String) session.getAttribute("toShow")).equals("Editing")){
            model.addAttribute("allHeaders", Business.getAllTitlesFromCommon());
            if ((String) session.getAttribute("startValues") == null || ((String) session.getAttribute("startValues")).equals("")) {
                model.addAttribute("editingAtt", new CommonNewsCreator2());
            }
            else {
                CommonNewsCreator commonNewsCreator = Business.getTheCommonNewByTitle((String) session.getAttribute("startValues"));
                session.setAttribute("chosen", commonNewsCreator.getHeader());
                model.addAttribute("chosen", commonNewsCreator);
                model.addAttribute("editingAtt", new CommonNewsCreator2());
            }

        }
        if ((String) session.getAttribute("exception") != null){
            model.addAttribute("exception", (String) session.getAttribute("exception"));
        }
        session.setAttribute("exception", null);

        return "start";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    // Это метод POST, который принимает @ModelAttribute произвольного класса, который заполняется
    // автоматически в форме, html код которой содержит поля этого класса
    // Все такие классы лежат в пакете helpful
    public String indexP(Model model, HttpSession session, @ModelAttribute("toLogIn") LoginAndPassword loginAndPassword) throws IOException, NoSuchAlgorithmException {
        boolean successLogin = Business.checkLoginAndPassword(loginAndPassword.getLogin(), bytesToHex(MessageDigest.getInstance("SHA-256").digest((loginAndPassword.getPassw()).getBytes()))  );
        session.setMaxInactiveInterval(360);
        session.setAttribute("successLogin", successLogin);
        session.setAttribute("login", loginAndPassword.getLogin());

        if (!successLogin){
            session.setAttribute("isLikeButtonActive", false);
            session.setAttribute("search", "");

            // model.addAttribute("search", new FindProperly(""));
            model.addAttribute("toLogIn", new LoginAndPassword());
            return "loginnew";
        }
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

    @GetMapping("/register")
    public String doRegister(Model model, HttpSession session){
        model.addAttribute("register", new RegistrationForm());
        if (session.getAttribute("register") != null){
            model.addAttribute("exception", (String) session.getAttribute("exception"));
        }
        
        return "register";
    }

    @PostMapping("/register")
    public String doRegisterPost(@ModelAttribute("register") RegistrationForm register, HttpSession session) throws NoSuchAlgorithmException {
        if (!bytesToHex(MessageDigest.getInstance("SHA-256").digest((register.getLogin()).getBytes())).equals(register.getCode())){
            session.setAttribute("exception", "Код неверный!");
            return "redirect:/register";
        }
        ServiceDatabase.addToUser(register.getLogin(), bytesToHex(MessageDigest.getInstance("SHA-256").digest((register.getPassword()).getBytes())), 2, Date.valueOf(LocalDate.now().toString()), 999999999, register.getName(), register.getSurname(), register.getIsu_number(), register.getBlock_id());
        return "redirect:/";
    }



    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }


}