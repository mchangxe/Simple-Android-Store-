package com.example.museum2015.sublime.FireBase;

import java.util.List;

import static android.R.id.list;

/**
 * Created by Museum2015 on 27/11/2016.
 */

public class ShopArticle {

    public ShopArticle(String title, String description, String picture, List<String> items){
        articleTitle = title;
        articleDescription = description;
        articlePicture = picture;
        listOfItems = items;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getArticlePicture() {
        return articlePicture;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public List<String> getListOfItems() {
        return listOfItems;
    }

    private String articleTitle;
    private String articlePicture;
    private String articleDescription;
    private List<String> listOfItems;

}
