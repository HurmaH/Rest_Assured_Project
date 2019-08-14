package com.app.utlities;

import java.util.ArrayList;
import java.util.List;

public class Etsy {

    /*
        Imagine this is API GET METHOD.
     */
    //GETAction(/search)
    public static List<String> search(String q) {
        List<String> matchingProducts = new ArrayList<>();

        for(String product : database() ) {
            if (product.toLowerCase().contains(q.toLowerCase())) {
                matchingProducts.add(product);
            }
        }


        return matchingProducts;

    }

    /*
       this method is acting like a database.
       Normally products need to be stored in Oracle,MySql, or
       any other database. this is just to practice
     */
    private static List<String> database() {

        List<String> productsDatabase = new ArrayList<>();
        productsDatabase.add("Custom wooden spoon");
        productsDatabase.add("Olive tree wooden spoon - nice");
        productsDatabase.add("Italian wallet");
        productsDatabase.add("Leather brown wallet");
        productsDatabase.add("American flag Umbrella");
        productsDatabase.add("Japanese flag Umbrella");
        productsDatabase.add("Wool white scarf");
        productsDatabase.add("Silk pink scarf");

        return productsDatabase;
    }

}