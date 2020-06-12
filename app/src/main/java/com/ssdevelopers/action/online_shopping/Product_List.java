package com.ssdevelopers.action.online_shopping;

import java.util.ArrayList;

public class Product_List {
    public static ArrayList<Product> products = new ArrayList<>();
    public static ArrayList<User> user = new ArrayList<>();
    public void add(Product pro){
        products.add(pro);
    }
    public int getSize(){
        return products.size();
    }
}
