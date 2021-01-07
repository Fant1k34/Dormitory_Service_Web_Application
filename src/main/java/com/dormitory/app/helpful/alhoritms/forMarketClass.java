package com.dormitory.app.helpful.alhoritms;

import com.dormitory.app.helpful.MarketNewsCreator;

public class forMarketClass {
    public static boolean filterBySearch(MarketNewsCreator marketNewsCreator, String word){
        if (word.equals("") || word.equals("\n")) return true;
        String A = marketNewsCreator.getTitle().toLowerCase() + " " + marketNewsCreator.getText_mark().toLowerCase();
        int a = A.length();
        String B = word.toLowerCase();
        int b = word.length();
        for (int i = 0; i < a - b; i++){
            int k = 0;
            for (int j = 0; j < b; j++){
                if (A.charAt(i + j) == B.charAt(j)){
                    k++;
                    if (k == b){
                        return true;
                    }
                    continue;
                }
                else {
                    k = 0;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        MarketNewsCreator marketNewsCreator = new MarketNewsCreator();
        marketNewsCreator.setTitle("Привет");
        marketNewsCreator.setAuthor(5);
        marketNewsCreator.setText_mark("Ого! А это описание!");
        System.out.println(filterBySearch(marketNewsCreator, "ПР"));
        System.out.println(filterBySearch(marketNewsCreator, "Фха"));
        System.out.println(filterBySearch(marketNewsCreator, "!"));
        System.out.println(filterBySearch(marketNewsCreator, "е"));
        System.out.println(filterBySearch(marketNewsCreator, "4"));
    }
}
