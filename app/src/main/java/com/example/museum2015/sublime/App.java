package com.example.museum2015.sublime;

import android.app.Application;

import com.auth0.android.result.Credentials;

/**
 * Created by Museum2015 on 4/12/2016.
 */

public class App extends Application {

    private Credentials mUserCredentials;

    private static App app;

    public static App getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public void saveUserCredentials(Credentials credentials){
        mUserCredentials = credentials;
    }

    public Credentials getmUserCredentials(){
        return mUserCredentials;
    }
}