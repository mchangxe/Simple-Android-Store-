package com.example.museum2015.sublime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainFeedView extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed_view);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


    }

    /*
     * App bar function
     * Inflate the menu items in menu_main.xml
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*
     * App bar function
     * Decides which screen the app should navigate to depending on which icon the user clicked on.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_me) {
            //show personal account screen
            return true;
        }

        if (id == R.id.action_articles){
            //show articles screen
            return true;
        }

        if (id == R.id.action_feed){
            //show main feed
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}