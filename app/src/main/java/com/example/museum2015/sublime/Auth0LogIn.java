package com.example.museum2015.sublime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.lock.AuthenticationCallback;
import com.auth0.android.lock.Lock;
import com.auth0.android.lock.LockCallback;
import com.auth0.android.lock.utils.LockException;
import com.auth0.android.result.Credentials;
import com.auth0.android.result.UserProfile;
import com.example.museum2015.sublime.Auth0Util.CredentialsManager;


/**
 * Created by Museum2015 on 4/12/2016.
 */


public class Auth0LogIn extends AppCompatActivity{

    //a user interface + functionality library by Auth0. Includes the default log in screen
    Lock lock;

    String userAccount;

    /*
     * When the first log in screen shows, create a mLock variable and start the library-included
     * activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Auth0 auth0 = new Auth0("KjUQNPc9H0z6gUlY726lRTCJeN38n7kv", "miconik.auth0.com");
        lock = Lock.newBuilder(auth0, callback)
                // Add parameters to the Lock Builder
                .build(this);

        startActivity(lock.newIntent(this));
    }

    /*
     * A callback decides what screen the log in screen will lead to depending on the authentication
     * process.
     */
    private LockCallback callback = new AuthenticationCallback() {
        /*
         * If log in was successful, start app
         */
        @Override
        public void onAuthentication(final Credentials credentials) {


            CredentialsManager.saveCredentials(getApplicationContext(), credentials);

            AuthenticationAPIClient client = new AuthenticationAPIClient(
                    new Auth0("KjUQNPc9H0z6gUlY726lRTCJeN38n7kv", "miconik.auth0.com"));

            client.tokenInfo(credentials.getIdToken())
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
            Intent home = new Intent(Auth0LogIn.this, MainFeedView.class);
            startActivity(home);
        }

        /*
         * If log in cancelled, stay on the same Lock screen and make a toast
         */
        @Override
        public void onCanceled() {
            // Login Cancelled response
            Toast.makeText(getApplicationContext(), "Log In - Cancelled",
                    Toast.LENGTH_SHORT).show();
        }

        /*
         * If log in failed, stay on the same lock screen and make a toast
         */
        @Override
        public void onError(LockException error){
            // Login Error response
            Toast.makeText(getApplicationContext(), "Log In - Error Occurred",
                    Toast.LENGTH_SHORT).show();
        }
    };

    /*
     * Destroy the mLock activity screen
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Your own Activity code
        lock.onDestroy(this);
        lock = null;
    }
}
