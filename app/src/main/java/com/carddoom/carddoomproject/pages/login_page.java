package com.carddoom.carddoomproject.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.carddoom.carddoomproject.databinding.ActivityLoginPageBinding;
import com.google.firebase.auth.FirebaseAuth;

public class login_page extends AppCompatActivity {
    ActivityLoginPageBinding b;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityLoginPageBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());


        checkIFAlreadyLogin();




    }//OnCreate

    public void checkIFAlreadyLogin() {

        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            //User is logged in
            startActivity(new Intent(this,Home_page.class));

        }else {

            startActivity(new Intent(this,login_page.class));

        }
    }


}