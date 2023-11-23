package com.example.cashregister;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {
    //create a list of items
    ArrayList<Item> itemList;
    //create a list of purchases
    ArrayList<History> purchaseList;

    public ArrayList<Item> getItemList() {
        if(itemList == null){
            itemList = new ArrayList<>(4);
            itemList.add(new Item("Pants",20.55,45));
            itemList.add(new Item("Dress",25.55,40));
            itemList.add(new Item("Shoes",40.55,55));
        }
        return itemList;
    }

    public ArrayList<History> getPurchaseList(){
        if(purchaseList == null){
            purchaseList = new ArrayList<>();
        }
        return purchaseList;
    }
}
