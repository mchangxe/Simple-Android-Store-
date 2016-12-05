package com.example.museum2015.sublime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.museum2015.sublime.FireBase.ServerFunctions;
import com.example.museum2015.sublime.FireBase.ShopItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainFeedView extends AppCompatActivity {

    private Toolbar toolbar;
    private GridView mGridView;
    private MainFeedAdapter mAdapter;
    private DatabaseReference mDataBase;
    private ServerFunctions serverHelper;
    private ArrayList<ShopItem> allItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed_view);

        //set up toolbar
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        //set up server
        mDataBase = FirebaseDatabase.getInstance().getReference();
        serverHelper = new ServerFunctions(mDataBase);
        allItems = serverHelper.retrieve();

        //set up grid view
        mGridView = (GridView) findViewById(R.id.gridview);

        mAdapter = new MainFeedAdapter(getApplicationContext(),
                R.layout.grid_item_main_feed,
                allItems);
        mGridView.setAdapter(mAdapter);
        Log.d("HERE", "onCreate: " + allItems.isEmpty());

        //add value listener to the "items" node of firebase tree
        final DatabaseReference items = mDataBase.child("items");
        items.addValueEventListener(new ValueEventListener() {

            /*
             * If there are changes to the items list on firebase, get the new list of shop items
             * and update gridview
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                allItems = serverHelper.retrieve();

                mAdapter = new MainFeedAdapter(getApplicationContext(),
                        R.layout.grid_item_main_feed,
                        allItems);
                mGridView.setAdapter(mAdapter);
                ArrayList<ShopItem> test = serverHelper.retrieve();
                Log.d("HERE", "onCreate: " + test.isEmpty());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                /*
                 * When clicks on a list item, show a toast with the state name. JUST FOR FUN
                 * @return void
                 */
                ShopItem currState = mAdapter.getItem(position);
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
            Intent navi = new Intent(MainFeedView.this, MainMeView.class);
            startActivity(navi);
            return true;
        }

        if (id == R.id.action_feed){
            //show main feed
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
