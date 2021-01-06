package com.dormitory.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SortedMarket {
    @RequestMapping("/sortByRating")
    public String sortByRating(){

        return "redirect:/market?sort=rating";
    }

    @RequestMapping("/sortByDate")
    public String sortByDate(){

        return "redirect:/market?sort=date";
    }

    @RequestMapping("/sortByName")
    public String sortByName(){

        return "redirect:/market?sort=name";
    }
}
