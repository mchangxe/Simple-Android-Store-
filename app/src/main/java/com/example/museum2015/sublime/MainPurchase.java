package com.example.museum2015.sublime;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.museum2015.sublime.FireBase.ServerFunctions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Museum2015 on 26/11/2016.
 */

public class MainPurchase extends AppCompatActivity {

    DatabaseReference mDataBase;
    ServerFunctions serverFunctions = new ServerFunctions(mDataBase);

    EditText addressLine1;
    EditText addressLine2;
    EditText addressName;
    EditText addressState;
    EditText addressCity;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.acitivty_main_purchase);

        //set up server
        mDataBase = FirebaseDatabase.getInstance().getReference();

        //get views
        addressLine1 = (EditText) findViewById(R.id.address_line_1);
        addressLine2 = (EditText) findViewById(R.id.address_line_2);
        addressName = (EditText) findViewById(R.id.address_name);
        addressState = (EditText) findViewById(R.id.address_State);
        addressCity = (EditText) findViewById(R.id.address_city);

        //set up button
        Button confirmButton = (Button) findViewById(R.id.confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uploadAddress()){
                    Intent successPurchased = new Intent(MainPurchase.this, MainThankYou.class);
                    startActivity(successPurchased);
                }
            }
        });
    }

    public boolean uploadAddress(){
        boolean success = true;
        if(addressLine1.getText().length()==0){
            Toast.makeText(getApplication().getBaseContext(), "FILL THIS PAGE PLEASE",
                    Toast.LENGTH_SHORT).show();

            success = false;
        }

        if(addressLine2.getText().length()==0){
            Toast.makeText(getApplication().getBaseContext(), "FILL THIS PAGE PLEASE",
                    Toast.LENGTH_SHORT).show();
            success = false;
        }

        if(addressName.getText().length()==0){
            Toast.makeText(getApplication().getBaseContext(), "FILL THIS PAGE PLEASE",
                    Toast.LENGTH_SHORT).show();
            success = false;
        }

        if(addressState.getText().length()==0){
            Toast.makeText(getApplication().getBaseContext(), "FILL THIS PAGE PLEASE",
                    Toast.LENGTH_SHORT).show();
            success = false;
        }

        if(addressCity.getText().length()==0){
            Toast.makeText(getApplication().getBaseContext(), "FILL THIS PAGE PLEASE",
                    Toast.LENGTH_SHORT).show();
            success = false;
        }

        return success;
    }

}
