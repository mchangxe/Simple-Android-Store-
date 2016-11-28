package com.example.museum2015.sublime.FireBase;

/**
 * Created by Museum2015 on 27/11/2016.
 * A Class for database usage that stands for an item in the store
 */

public class ShopItem {

    public ShopItem(String Name, String Brand, String Category, String Condition,
                    double Price, String Picture, String Description, String Seller){
        name = Name;
        brand = Brand;
        category = Category;
        condition = Condition;
        price = Price;
        picture = Picture;
        description = Description;
        seller = Seller;

    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public String getCondition() {
        return condition;
    }

    public double getPrice() {
        return price;
    }

    public String getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public String getSeller(){
        return seller;
    }

    private String name;
    private String brand;
    private String category;
    private String condition;
    private double price;
    private String picture;
    private String description;
    private String seller;



}
