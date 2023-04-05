package com.example.foodster_app_mobileappdevproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RestaurantOpeningActivity extends AppCompatActivity {
    DataBaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_opening);
        TextView txtWelcome = findViewById(R.id.txtWelcome);
        Button btnAddRemove = findViewById(R.id.btnAddRemove);
        Button btnSendRemind = findViewById(R.id.btnSendRemind);
        Button btnGenReport = findViewById(R.id.btnGenReport);
        Button btnLogOut = findViewById(R.id.btnRlogOut);

        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String restaurantId = preferences.getString("RestaurantID", "defaultValue");
        String password = preferences.getString("PasswordOfRestaurant", "defaultValue");
        String restaurantName = preferences.getString("RestaurantName", "defaultValue");
        String firstNameOwner = preferences.getString("FirstNameOwner", "defaultValue");
        String lastNameOwner = preferences.getString("LastNameOwner", "defaultValue");
        String phoneNumberRestaurant = preferences.getString("PhoneNumberRestaurant", "defaultValue");
        String city = preferences.getString("City", "defaultValue");
        String address = preferences.getString("AddressRestaurant", "defaultValue");
        String email = preferences.getString("RestaurantEmail", "defaultValue");
        txtWelcome.setText("Welcome, "+firstNameOwner+"!");

        btnAddRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestaurantOpeningActivity.this,AddNewFoodActivity.class));
            }
        });



        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestaurantOpeningActivity.this,MainActivityLogin.class));
                finishAffinity();
                SharedPreferences.Editor editorToDelete = preferences.edit();
                editorToDelete.clear();
                editorToDelete.commit();
            }
        });
        btnSendRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestaurantOpeningActivity.this,SendReminderActivity.class));
            }
        });
        btnGenReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestaurantOpeningActivity.this,RestaurantOrdersViewEditActivity.class));
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigationRestDel);

        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        startActivity(new Intent(RestaurantOpeningActivity.this, RestaurantOpeningActivity.class));
                        return true;
                    case R.id.navigation_orders:
                        startActivity(new Intent(RestaurantOpeningActivity.this, GenerateReportsActivity.class));
                        return true;
                    case R.id.navigation_profile:
                        startActivity(new Intent(RestaurantOpeningActivity.this, RestaurantProfile.class));
                        return true;
                }
                return false;
            }

        });
    }
}