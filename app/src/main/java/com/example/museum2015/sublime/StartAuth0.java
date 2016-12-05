package com.example.museum2015.sublime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Museum2015 on 4/12/2016.
 */

public class StartAuth0 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Intent lockIntent = new Intent(this, Auth0LogIn.class);
        startActivity(lockIntent);
    }
}
