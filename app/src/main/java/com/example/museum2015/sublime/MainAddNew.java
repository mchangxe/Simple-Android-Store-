package com.example.museum2015.sublime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.EditText;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.result.UserProfile;
import com.example.museum2015.sublime.FireBase.ServerFunctions;
import com.example.museum2015.sublime.FireBase.ShopItem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.String.valueOf;

/**
 * Created by Museum2015 on 4/12/2016.
 */

public class MainAddNew extends AppCompatActivity{

    private DatabaseReference mDataBase;
    private ServerFunctions serverHelper;

    EditText name;
    EditText category;
    EditText brand;
    EditText condition;
    EditText price;
    EditText description;
    Button addButton;

    String userAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_new_item);

        //set up server
        mDataBase = FirebaseDatabase.getInstance().getReference();
        serverHelper = new ServerFunctions(mDataBase);

        //get views
        brand= (EditText) findViewById(R.id.brand);
        name = (EditText) findViewById(R.id.name);
        category = (EditText) findViewById(R.id.category);
        price = (EditText) findViewById(R.id.price);
        condition= (EditText) findViewById(R.id.condition);
        description= (EditText) findViewById(R.id.description);

        addButton = (Button) findViewById(R.id.add);

        //get user account


        //set button onclick listener
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkFields()) {

                    serverHelper.addNewItem(
                            name.getText().toString(),
                            brand.getText().toString(),
                            category.getText().toString(),
                            condition.getText().toString(),
                            price.getText().toString(),
                            description.getText().toString(),
                            userAccount);

                    Intent navi = new Intent(MainAddNew.this, MainFeedView.class);
                    startActivity(navi);
                }
            }
        });
    }


    /*
     * This function will check if the page is successfully filled, if any of the fields is not filled
     * then a toast will be displayed.
     */
    public boolean checkFields(){
        boolean success = true;
        if(name.getText().length()==0){
            Toast.makeText(getApplication().getBaseContext(), "FILL THIS PAGE PLEASE",
                    Toast.LENGTH_SHORT).show();

            success = false;
        }

        if(brand.getText().length()==0){
            Toast.makeText(getApplication().getBaseContext(), "FILL THIS PAGE PLEASE",
                    Toast.LENGTH_SHORT).show();
            success = false;
        }

        if(price.getText().length()==0){
            Toast.makeText(getApplication().getBaseContext(), "FILL THIS PAGE PLEASE",
                    Toast.LENGTH_SHORT).show();
            success = false;
        }

        if(condition.getText().length()==0){
            Toast.makeText(getApplication().getBaseContext(), "FILL THIS PAGE PLEASE",
                    Toast.LENGTH_SHORT).show();
            success = false;
        }

        if(category.getText().length()==0){
            Toast.makeText(getApplication().getBaseContext(), "FILL THIS PAGE PLEASE",
                    Toast.LENGTH_SHORT).show();
            success = false;
        }

        if(description.getText().length()==0){
            Toast.makeText(getApplication().getBaseContext(), "FILL THIS PAGE PLEASE",
                    Toast.LENGTH_SHORT).show();
            success = false;
        }

        return success;
    }

    /*
     * An auth0 feature that can get the user email account from the client. If sucessfully
     * authenticated, the member varibale userAccount will be set to the email account.
     * (A REACH FEATURE)
     */
    public void getUserAccount(){
        AuthenticationAPIClient client = new AuthenticationAPIClient(
                new Auth0("KjUQNPc9H0z6gUlY726lRTCJeN38n7kv", "miconik.auth0.com"));

        client.tokenInfo(App.getInstance().getmUserCredentials().getIdToken())
                .start(new BaseCallback<UserProfile, AuthenticationException>() {
                    @Override
                    public void onSuccess(UserProfile payload){
                        userAccount = payload.getEmail();
                    }

                    @Override
                    public void onFailure(AuthenticationException error){
                        Toast.makeText(getApplicationContext(), "Get User Account Failed",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
