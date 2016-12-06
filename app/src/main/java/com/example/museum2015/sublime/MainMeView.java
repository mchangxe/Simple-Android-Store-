package com.example.museum2015.sublime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.museum2015.sublime.FireBase.ServerFunctions;
import com.example.museum2015.sublime.FireBase.ShopItem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Museum2015 on 26/11/2016.
 */

public class MainMeView extends AppCompatActivity{

    private Toolbar toolbar;
    private GridView mGridView;
    private MainFeedAdapter mAdapter;
    private DatabaseReference mDataBase;
    private ServerFunctions serverHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_my_items);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Account");

        //set up server
        mDataBase = FirebaseDatabase.getInstance().getReference();
        serverHelper = new ServerFunctions(mDataBase);

        //set up grid view
        mGridView = (GridView) findViewById(R.id.gridview);

        mAdapter = new MainFeedAdapter(this, R.layout.grid_item_main_feed, serverHelper.retrieve());
        mGridView.setAdapter(mAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                /*
                 * When clicks on a list item, show a toast with the state name. JUST FOR FUN
                 * @return void
                 */
                ShopItem currState = (ShopItem)mAdapter.getItem(position);
                Intent detailIntent = new Intent(adapterView.getContext(), DetailItemView.class);
                detailIntent.putExtra("ShopItem", currState);
                startActivity(detailIntent);
            }
        });


    }

    /*
     * App bar function
     * Inflate the menu items in menu_main.xml
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_me, menu);
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
        if (id == R.id.action_add_new_item) {
            //add new item

            Intent addNew = new Intent(MainMeView.this, MainAddNew.class);
            startActivity(addNew);
            return true;
        }


        if (id == R.id.action_feed){
            //show main feed
            Intent navi = new Intent(MainMeView.this, MainFeedView.class);
            startActivity(navi);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
