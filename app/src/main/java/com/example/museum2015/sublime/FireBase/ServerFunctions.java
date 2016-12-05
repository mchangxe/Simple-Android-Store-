package com.example.museum2015.sublime.FireBase;

import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Museum2015 on 27/11/2016.
 */

public class ServerFunctions {

    private DatabaseReference mDatabase;

    private ArrayList<ShopItem> allitems = new ArrayList<>();

    public DatabaseReference getmDatabase(){
        return mDatabase;
    }

    public ServerFunctions(){
        mDatabase = null;
    }

    public ServerFunctions(DatabaseReference dataBase){
        mDatabase = dataBase;
    }

    /*
     * Add a new item to firebase server
     * @param fields needed to describe a new shop item
     * @return void
     */
    public void addNewItem(String name, String brand, String category, String condition,
                        String price, String description, String seller){
        ShopItem newItem = new ShopItem(name, brand, category, condition,
        price, description, seller);

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
     * Retrieve from server
     */
    public ArrayList<ShopItem> retrieve(){

//        mDatabase.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                allitems = fetchData(dataSnapshot);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                allitems = fetchData(dataSnapshot);
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                fetchData(dataSnapshot);
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        mDatabase.child("items").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ShopItem item= dataSnapshot.getValue(ShopItem.class);
                allitems.add(item);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                ShopItem item= dataSnapshot.getValue(ShopItem.class);
                allitems.add(item);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Log.d("HERE", "retrieve: " +allitems.isEmpty());
        return allitems;
    }

    /*
     * Fetch data from server
     * @param dataSnapshot in the form of a ShopItem class object
     */
    /*private ArrayList<ShopItem> fetchData(DataSnapshot dataSnapshot)
    {
        if (!allitems.isEmpty()) {
            allitems.clear();
        }

        for (DataSnapshot ds: dataSnapshot.getChildren())
        {
            ShopItem name = ds.getValue(ShopItem.class);
            allitems.add(name);
        }
        //Log.d("NEXT", "retrieve: " +allitems.isEmpty());
        return allitems;
    }*/

}
