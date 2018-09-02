package com.example.cheewei.listview;

public class Item {
    private String itemname;
    private double price;

    public Item(String itemname,double price){
        this.itemname=itemname;
        this.price=price;
    }

    public String getItemname(){
        return itemname;
    }

    public double getPrice(){
        return price;
    }

}
