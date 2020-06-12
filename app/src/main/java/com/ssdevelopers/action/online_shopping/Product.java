package com.ssdevelopers.action.online_shopping;

import android.net.Uri;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {
    String name;
    ArrayList<Uri> img;
    String price;
    String delivery_date;
    String Placed_on;
    ArrayList<Integer> color ;
    ArrayList<String> size;
    String delivery_address;
    int numer_of_view = 0;

    public Product() {
    }

    public Product(String name, ArrayList<Uri> img, String price) {
        this.name = name;
        this.img = img;
        this.price = price;
    }

    public int getNumer_of_view() {
        return numer_of_view;
    }

    public void setNumer_of_view(int numer_of_view) {
        this.numer_of_view = numer_of_view;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(ArrayList<Uri> img) {
        this.img = img;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public void setPlaced_on(String placed_on) {
        Placed_on = placed_on;
    }

    public void setColor( ArrayList<Integer> color) {
        this.color = color;
    }

    public void setSize(ArrayList<String> size) {
        this.size = size;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }
}
