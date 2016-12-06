package com.example.museum2015.sublime;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.museum2015.sublime.FireBase.ShopItem;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.String.valueOf;

/**
 * Created by Museum2015 on 2/12/2016.
 */

public class MainFeedAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<ShopItem> items;


    public MainFeedAdapter(Context context, int resoures, ArrayList<ShopItem> retrieve) {
        mContext = context;
        items = retrieve;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }


    /*
     * Custom getView method for the gridView page, grabs the appropriate textboxes and populate them
     * with information
     */
    @NonNull
    @Override
    public View getView(int position, View v, ViewGroup parent){
        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            v = inflater.inflate(R.layout.grid_item_main_feed, null);
        }

        ShopItem currItems = (ShopItem) getItem(position);

        TextView itemName = (TextView)v.findViewById(R.id.itemName);
        TextView itemPrice = (TextView)v.findViewById(R.id.itemPrice);
        TextView itemCategory = (TextView)v.findViewById(R.id.itemCategory);

        if (currItems != null) {
            itemName.setText(currItems.getName());
            itemPrice.setText(valueOf(currItems.getPrice()));
            itemCategory.setText(currItems.getCategory());
        }

        return v;
    }

}
