package com.example.museum2015.sublime;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.museum2015.sublime.FireBase.ShopItem;

import java.util.ArrayList;

import static java.lang.String.valueOf;

/**
 * Created by Museum2015 on 2/12/2016.
 */

public class MainFeedAdapter extends ArrayAdapter<ShopItem> {

    public MainFeedAdapter(Context context, int resource, ArrayList<ShopItem> retrieve) {
        super(context, resource);
    }

    /*
     * Custom getView method for the listView page, grabs the appropriate textboxes and populate them
     * with information
     */
    @NonNull
    @Override
    public View getView(int position, View v, ViewGroup parent){
        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.grid_item_main_feed, null);
        }

        ShopItem currItems = getItem(position);

        TextView itemName = (TextView)v.findViewById(R.id.itemName);
        TextView itemPrice = (TextView)v.findViewById(R.id.itemPrice);
        TextView itemCategory = (TextView)v.findViewById(R.id.item_category);

        itemName.setText(currItems.getName());
        itemPrice.setText(valueOf(currItems.getPrice()));
        itemCategory.setText(currItems.getCategory());

        return v;
    }

}
