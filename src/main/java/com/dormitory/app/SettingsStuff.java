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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.dormitory.app.Start.bytesToHex;

@Controller
public class SettingsStuff {
    @RequestMapping(value = "/changepassword", method = RequestMethod.GET)
    public String changePassword(HttpSession session, Model model){
        if (session.getAttribute("login") == null){
            return "redirect:/";
        }
        if (session.getAttribute("exception") != null) {
            model.addAttribute("exception", session.getAttribute("exception"));
            session.setAttribute("exception", null);
        }
        model.addAttribute("password", new PasswordChanger());
        return "changepassword";
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public String changePasswordPost(HttpSession session, @ModelAttribute("password") PasswordChanger password) throws NoSuchAlgorithmException {
        if (session.getAttribute("login") == null){
            return "redirect:/";
        }
        if (!Business.checkLoginAndPassword((String) session.getAttribute("login"), bytesToHex(MessageDigest.getInstance("SHA-256").digest((password.getPrevPass()).getBytes())))){
            session.setAttribute("exception", "Пароль неверный!");
            return "redirect:/changepassword";
        }
        if (password.getNewPass1().length() < 8 || password.getNewPass1().length() > 21){
            session.setAttribute("exception", "Неверная длина пароля!");
            return "redirect:/changepassword";
        }
        if (!password.getNewPass1().equals(password.getNewPass2())){
            session.setAttribute("exception", "Новые пароли не совпадают");
            return "redirect:/changepassword";
        }
        Business.updatePassword(bytesToHex(MessageDigest.getInstance("SHA-256").digest((password.getPrevPass()).getBytes())), bytesToHex(MessageDigest.getInstance("SHA-256").digest((password.getNewPass1()).getBytes())));
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
    public String buy(HttpSession session){
        if (session.getAttribute("login") == null){
            return "redirect:/";
        }
        return "buy";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String buyPost(HttpSession session){
        if (session.getAttribute("login") == null){
            return "redirect:/";
        }
        if ((int) session.getAttribute("group_id") == 0 || (int) session.getAttribute("group_id") == 1) {
            return "redirect:/";
        }
        Business.updateUserGroupCodeByLogin((String) session.getAttribute("login"));
        session.invalidate();
        return "redirect:/";
    }


}
