package com.carddoom.carddoomproject.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.carddoom.carddoomproject.R;
import com.carddoom.carddoomproject.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class Home_page extends AppCompatActivity {
    ActivityHomeBinding b;
   public static BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());



        //BottomNavigation
        setBottomNavigationItems();


    }//out on create

    public void setBottomNavigationItems() {

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set default  Selected
        bottomNavigationView.setSelectedItemId(R.id.home_menu);

        bottomNavigationView.setOnItemSelectedListener(item -> {


            switch (item.getItemId()) {
                case R.id.home_menu:
                    // Switch to page one
                    startActivity(new Intent(this, Home_page.class));
                    break;
                case R.id.wallet:
                    // Switch to page two
                    startActivity(new Intent(this, Home_page.class));
                    break;
                case R.id.transaction:
                    // Switch to page three
                    startActivity(new Intent(this, Transaction_page.class));
                    break;
                case R.id.account:
                    // Switch to page three
                    startActivity(new Intent(this, Home_page.class));
                    break;
                case R.id.whatsapp:
                    // Switch to page three
                    startActivity(new Intent(this, Home_page.class));
                    break;

            }


            return true;
        });


    }


}