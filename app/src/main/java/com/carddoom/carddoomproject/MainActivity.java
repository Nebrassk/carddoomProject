package com.carddoom.carddoomproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.carddoom.carddoomproject.databinding.ActivityMainBinding;
import com.carddoom.carddoomproject.pages.Home_page;
import com.carddoom.carddoomproject.pages.SignUp_page;
import com.carddoom.carddoomproject.pages.login_page;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding b;

    @Override
    protected void onStart() {
        super.onStart();

        checkIFAlreadyLogin();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());





        b.btnLogin.setOnClickListener(view -> {

            startActivity(new Intent(this, login_page.class));
        });
        b.btnSignUp.setOnClickListener(view -> {

            startActivity(new Intent(this, SignUp_page.class));
        });





    }//out



    public void checkIFAlreadyLogin() {

        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            //User is logged in
            startActivity(new Intent(MainActivity.this, Home_page.class));

        }else {

            startActivity(new Intent(MainActivity.this,login_page.class));

        }
    }
}