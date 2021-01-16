package com.dormitory.app;

import com.dormitory.app.database.Business;
import com.dormitory.app.helpful.PasswordChanger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class SettingsStuff {
    @RequestMapping(value = "/changepassword", method = RequestMethod.GET)
    public String changePassword(HttpSession session, Model model){
        if (session.getAttribute("exception") != null) {
            model.addAttribute("exception", session.getAttribute("exception"));
            session.setAttribute("exception", null);
        }
        model.addAttribute("password", new PasswordChanger());
        return "changepassword";
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public String changePasswordPost(HttpSession session, @ModelAttribute("password") PasswordChanger password){
        if (!Business.checkLoginAndPassword((String) session.getAttribute("login"), password.getPrevPass())){
            session.setAttribute("exception", "Пароль неверный!");
            return "redirect:/changepassword";
        }
        if (!password.getNewPass1().equals(password.getNewPass2())){
            session.setAttribute("exception", "Новые пароли не совпадают");
            return "redirect:/changepassword";
        }
        Business.updatePassword(password.getPrevPass(), password.getNewPass1());
        return "redirect:/";
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exit(HttpSession session) throws InterruptedException {
        session.invalidate();
        session.setMaxInactiveInterval(0);
        Thread.sleep(1020);
        return "redirect:/";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public String buy(){
        return "buy";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String buyPost(HttpSession session){
        if ((int) session.getAttribute("group_id") == 0 || (int) session.getAttribute("group_id") == 1) {
            return "redirect:/";
        }
        Business.updateUserGroupCodeByLogin((String) session.getAttribute("login"));
        return "redirect:/";
    }


}
