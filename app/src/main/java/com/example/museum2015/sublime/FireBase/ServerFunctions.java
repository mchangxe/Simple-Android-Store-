package com.example.museum2015.sublime.FireBase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;
import java.util.List;

/**
 * Created by Museum2015 on 27/11/2016.
 */

public class ServerFunctions {

    private DatabaseReference mDatabase;

    public ServerFunctions(DatabaseReference dataBase){
        mDatabase = dataBase;
    }

    public void setUpFirebase(){

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void addNewItem(String name, String brand, String category, String condition,
                        double price, String picture, String description, String seller){

        ShopItem newItem = new ShopItem(name, brand, category, condition,
        price, picture, description, seller);

        mDatabase.child("items").setValue(name+brand);
        mDatabase.child("items").child(name+brand).setValue(newItem);

    }

    public void deleteItem(String itemName){
        DatabaseReference itemRef = mDatabase.child("items").child(itemName);
        itemRef.removeValue();
    }

    public void addNewArticle(String title, String description, String picture, List<String> items){

        ShopArticle newArticle = new ShopArticle(title, description, picture, items);

        mDatabase.child("articles").child(title).setValue(newArticle);

    }

    public void deleteArticle(String artcileTitle){

        DatabaseReference articleRef = mDatabase.child("articles").child(artcileTitle);
        articleRef.removeValue();
    }


}
