package com.example.museum2015.sublime;

import android.support.annotation.NonNull;

import com.example.museum2015.sublime.FireBase.ShopItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * Created by Museum2015 on 27/11/2016.
 */

public class FireBaseTest {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference tDatabase = database.getReference();

    /*
     * Test adding a new shop item to the server.
     */
    @Test
    public void testAddNewItem() throws InterruptedException {
        final CountDownLatch writeSignal = new CountDownLatch(1);

        ShopItem testItem =
                new ShopItem("name", "brand", "cate", "condi", 1000, "pic", "description", "me");

        tDatabase.child("items").setValue("test1");

        tDatabase.child("items").child("test1").setValue(testItem)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                writeSignal.countDown();
            }
        });

        writeSignal.await(10, TimeUnit.SECONDS);
    }

    /*
     * Test removing a shop item on the server.
     */
    @Test
    public void testRemoveItem() throws InterruptedException {
        final CountDownLatch writeSignal = new CountDownLatch(1);

        DatabaseReference item = tDatabase.child("items").child("test1");

        item.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                writeSignal.countDown();
            }
        });

        writeSignal.await(10, TimeUnit.SECONDS);
    }

    /*
     * Test adding a new article to the server, although adding a new article will always be done
     * directly on the server by administrator when the app is released.
     */
    @Test
    public void testAddNewArticle() throws InterruptedException {
        final CountDownLatch writeSignal = new CountDownLatch(1);

        List<String> testListOfItems = new ArrayList<String>();
        testListOfItems.add("1");
        testListOfItems.add("2");
        ShopArticle testArticle = new ShopArticle("Test", "This is a test", "Pic", testListOfItems);

        tDatabase.child("articles").setValue("test1");

        tDatabase.child("articles").child("test1")
                .setValue(testArticle).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                writeSignal.countDown();
            }
        });

        writeSignal.await(10, TimeUnit.SECONDS);
    }

    /*
     * Test removing an article to the server, although removing a new article will always be done
     * directly on the server by administrator when the app is released.
     */
    @Test
    public void testRemoveArticle() throws InterruptedException {
        final CountDownLatch writeSignal = new CountDownLatch(1);

        DatabaseReference article = tDatabase.child("articles").child("test1");

       article.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                writeSignal.countDown();
            }
        });

        writeSignal.await(10, TimeUnit.SECONDS);
    }
}
