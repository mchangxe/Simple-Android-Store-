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

    /*
     * Sets up the database.
     */
    public void setUpFirebase(){

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    /*
     * Add a new item to firebase server
     * @param fields needed to describe a new shop item
     * @return void
     */
    public void addNewItem(String name, String brand, String category, String condition,
                        double price, String picture, String description, String seller){

        ShopItem newItem = new ShopItem(name, brand, category, condition,
        price, picture, description, seller);

        mDatabase.child("items").setValue(name+brand);
        mDatabase.child("items").child(name+brand).setValue(newItem);

    }

    /*
     * Remove an item from firebase server
     * @param itemName the identifier of the item to be deleted on the server
     * @return void
     */
    public void deleteItem(String itemName){
        DatabaseReference itemRef = mDatabase.child("items").child(itemName);
        itemRef.removeValue();
    }

    /*
     * Add a new article to firebase server
     * @param fields needed to describe a new article
     * @return void
     */
    public void addNewArticle(String title, String description, String picture, List<String> items){

        ShopArticle newArticle = new ShopArticle(title, description, picture, items);

        mDatabase.child("articles").child(title).setValue(newArticle);

    }

    /*
     * Remove an article from firebase server
     * @param articleTitle the identifier of the article to be deleted on the server
     * @return void
     */
    public void deleteArticle(String artcileTitle){

        DatabaseReference articleRef = mDatabase.child("articles").child(artcileTitle);
        articleRef.removeValue();
    }


}
