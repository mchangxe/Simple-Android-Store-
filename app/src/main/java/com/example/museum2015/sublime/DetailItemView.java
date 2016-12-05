package com.example.museum2015.sublime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.museum2015.sublime.FireBase.ShopItem;

import static java.lang.String.valueOf;

/**
 * Created by Museum2015 on 26/11/2016.
 */

public class DetailItemView extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_item_detail_view);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        //get info from bundle
        Bundle bundle = getIntent().getExtras();
        ShopItem item = bundle.getParcelable("ShopItem");

        //get views
        TextView brandText = (TextView) findViewById(R.id.item_brand);
        TextView nameText = (TextView) findViewById(R.id.item_name);
        TextView categoryText = (TextView) findViewById(R.id.item_category);
        TextView priceText = (TextView) findViewById(R.id.item_price);
        TextView conditionText = (TextView) findViewById(R.id.item_condition);
        TextView descriptionText = (TextView) findViewById(R.id.item_description);

        Button purchaseButton = (Button) findViewById(R.id.purchase);

        //set views
        brandText.setText(item.getBrand());
        nameText.setText(item.getName());
        categoryText.setText(item.getCategory());
        priceText.setText(valueOf(item.getPrice()));
        conditionText.setText(item.getCondition());
        descriptionText.setText(item.getDescription());

        //set button onclick listener
        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent purchasePage = new Intent(DetailItemView.this, MainPurchase.class);
                startActivity(purchasePage);
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
            return true;
        }

        if (id == R.id.action_feed){
            //show main feed
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
