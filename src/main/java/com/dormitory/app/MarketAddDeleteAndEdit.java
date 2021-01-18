package com.dormitory.app;

import com.dormitory.app.database.Business;
import com.dormitory.app.database.InsertData;
import com.dormitory.app.helpful.CommonNewsCreator;
import com.dormitory.app.helpful.MarketNewsCreator;
import com.dormitory.app.helpful.MarketSimpleFromFormGetter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class MarketAddDeleteAndEdit {
    @RequestMapping(value = "/addMarketNews", method = RequestMethod.GET)
    public String addMarket(Model model, @RequestParam(value = "pictureId", defaultValue = "") String pictureId, HttpSession session) throws InterruptedException {
        if (session.getAttribute("login") == null){
            return "redirect:/";
        }
        if (pictureId.equals("")) {
            model.addAttribute("pictureId", "");
        }
        else {
            try{
                Integer.valueOf(pictureId);
                model.addAttribute("pictureId", pictureId);
            }
            catch (Exception e){
                model.addAttribute("pictureId", "");
            }
        }
        model.addAttribute("makeMarketNew", new MarketSimpleFromFormGetter());

        if ((int) session.getAttribute("group_id") == 2){
            session.setAttribute("exception", "Получите МЕГА-версию, чтобы не ждать");
            model.addAttribute("exception", (String) session.getAttribute("exception"));
            session.setAttribute("exception", null);
            Thread.sleep(7000);
        }

        return "addmarket";
    }


    @RequestMapping(value = "/addMarketNews", method = RequestMethod.POST)
    public String addMarketPost(Model model, @ModelAttribute("makeMarketNew") MarketSimpleFromFormGetter marketSimpleFromFormGetter, HttpSession session) throws InterruptedException {
        String id = "";
        try{
        if (session.getAttribute("login") == null){
            return "redirect:/";
        }
        MarketNewsCreator marketNewsCreator = new MarketNewsCreator();
        marketNewsCreator.setTitle(marketSimpleFromFormGetter.getTitleForMarketSimpleFromFormGetter());
        marketNewsCreator.setText_mark(marketSimpleFromFormGetter.getTextForMarketSimpleFromFormGetter());
        marketNewsCreator.setAuthor(Business.getUserIdByLogin((String) session.getAttribute("login")));
        marketNewsCreator.setDate_mark(String.valueOf(LocalDate.now()));
        marketNewsCreator.setLiked(false);
        marketNewsCreator.setRating(0);
        marketNewsCreator.setContact_info(marketSimpleFromFormGetter.getContactInfoForMarketSimpleFromFormGetter());
        marketNewsCreator.setTag_id_mark(3);

        id = InsertData.insertToMarket(marketNewsCreator);

        // Кладу в БД правильное id объявления с правильным id картинки
        InsertData.putPictureToDBRelatedToNewById(Integer.parseInt(marketSimpleFromFormGetter.getImageNameForMarketSimpleFromFormGetter()), Integer.parseInt(id), Business.getPictureFromDBByIdPicture(Integer.parseInt(marketSimpleFromFormGetter.getImageNameForMarketSimpleFromFormGetter())).getBytes());

        return "redirect:/market";
        }
        catch (Exception e){
            return "redirect:/market/items/del/" + id;
        }
    }

    @RequestMapping("/market/items/del/{id}")
    public String deleteMarketNews(@PathVariable("id") String id, HttpSession session){
        if (session.getAttribute("login") == null){
            return "redirect:/";
        }
        int newsToDelete = Integer.parseInt(id);
        int groupId = (int) session.getAttribute("group_id");
        Business.deleteByMarketNewsID(newsToDelete, groupId, (String) session.getAttribute("login"));
        return "redirect:/market";
    }


    @RequestMapping("/market/items/info/{id}")
    public String showInfo(@PathVariable("id") String id, HttpSession session) throws InterruptedException {
        if (session.getAttribute("login") == null){
            return "redirect:/";
        }
        if ((int) session.getAttribute("group_id") == 2){
            session.setAttribute("exception", "Получите МЕГА-версию, чтобы не ждать");
            Thread.sleep(7000);
        }
        ArrayList<String> arrayList = Business.showAuthorInfoByMarkId(Integer.parseInt(id));
        session.setAttribute("contactInfo", arrayList.get(0));
        session.setAttribute("blockId", arrayList.get(1));
        return "redirect:/market";
    }
}
