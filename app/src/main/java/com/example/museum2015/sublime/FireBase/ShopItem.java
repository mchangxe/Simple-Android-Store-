package com.example.museum2015.sublime.FireBase;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Museum2015 on 27/11/2016.
 * A Class for database usage that stands for an item in the store
 */

public class ShopItem implements Parcelable {

    public ShopItem() {
        name = null;
        brand = null;
        category = null;
        condition = null;
        price = null;
        description = null;
        seller = null;

    }

    public ShopItem(String Name, String Brand, String Category, String Condition,
                    String Price, String Description, String Seller){
        name = Name;
        brand = Brand;
        category = Category;
        condition = Condition;
        price = Price;
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

    public String getPrice() {
        return price;
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
    private String price;
    private String description;
    private String seller;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.brand);
        dest.writeString(this.category);
        dest.writeString(this.condition);
        dest.writeString(this.price);
        dest.writeString(this.description);
        dest.writeString(this.seller);
    }

    protected ShopItem(Parcel in) {
        this.name = in.readString();
        this.brand = in.readString();
        this.category = in.readString();
        this.condition = in.readString();
        this.price = in.readString();
        this.description = in.readString();
        this.seller = in.readString();
    }

    public static final Parcelable.Creator<ShopItem> CREATOR = new Parcelable.Creator<ShopItem>() {
        @Override
        public ShopItem createFromParcel(Parcel source) {
            return new ShopItem(source);
        }

        @Override
        public ShopItem[] newArray(int size) {
            return new ShopItem[size];
        }
    };
}
