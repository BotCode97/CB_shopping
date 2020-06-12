package com.ssdevelopers.action.online_shopping;

import android.net.Uri;

import java.util.ArrayList;

public class User {
    Uri profile_img;
    String name;
    String email;
    String password;
    String dob;
    String type;
    public static ArrayList<Orders> cart = new ArrayList<>();

    public User(Uri img,String name, String email, String password, String dob,String type) {
        profile_img = img;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.type = type;
    }

    private static class Orders{
        String name;
        Uri img;
        String price;
        int color ;
        String size;
        int qty;

        public Orders(String name, Uri img, String price, int color, String size, int qty) {
            this.name = name;
            this.img = img;
            this.price = price;
            this.color = color;
            this.size = size;
            this.qty = qty;
        }
    }
}
