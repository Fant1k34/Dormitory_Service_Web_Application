package com.dormitory.app;

import com.dormitory.app.database.Business;
import com.dormitory.app.helpful.MarketNewsCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;

@Controller
public class Market {
    @RequestMapping(value = "/market", method = RequestMethod.GET)
    public String marketPage(Model model){
        model.addAttribute("marketNews", putAllNewsToModel());
        return "market";
    }

    @RequestMapping(value = "/service")
    @ResponseBody
    public String service(){
        return "";
    }

    public static ArrayList<MarketNewsCreator> putAllNewsToModel(){
        ArrayList<MarketNewsCreator> marketNewsCreatorArrayList = Business.putAllNewsToMarketNewsCreator();
        Collections.sort(marketNewsCreatorArrayList);


        return marketNewsCreatorArrayList;
    }
}
