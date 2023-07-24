package com.carddoom.carddoomproject.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.carddoom.carddoomproject.R;
import com.carddoom.carddoomproject.databinding.ActivityTransactionPageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Transaction_page extends AppCompatActivity {
    ActivityTransactionPageBinding b;
    BottomNavigationView bottomNavigationView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityTransactionPageBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());



      //Bottom Navigation Items
      setBottomNavigationItems();

    }//

    public void setBottomNavigationItems() {

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.transaction);

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