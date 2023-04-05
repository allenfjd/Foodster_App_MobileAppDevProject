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

public class RestaurantProfile extends AppCompatActivity {

    TextView RestaurantFirstName;
    TextView RestaurantLastName;
    TextView RestaurantMail;
    TextView RestaurantPhone;
    TextView RestaurantAdress;
    TextView Restaurant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);
        RestaurantFirstName=findViewById(R.id.txtRName);
        RestaurantLastName=findViewById(R.id.txtRLName);
        RestaurantMail=findViewById(R.id.txtREmail);
        RestaurantPhone=findViewById(R.id.txtRPhone);
        RestaurantAdress=findViewById(R.id.txtRAdress);
        Restaurant=findViewById(R.id.txtRRestaurant);
        Button btnEditProfile=findViewById(R.id.btnCEditProfile);
        Button btnLogout=findViewById(R.id.btnRSaveChanges);

        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String email = preferences.getString("CustomerEmail", "defaultValue");
        String restaurantId = preferences.getString("RestaurantID", "defaultValue");
        String password = preferences.getString("PasswordOfRestaurant", "defaultValue");
        String restaurantName = preferences.getString("RestaurantName", "defaultValue");
        String firstNameOwner = preferences.getString("FirstNameOwner", "defaultValue");
        String lastNameOwner = preferences.getString("LastNameOwner", "defaultValue");
        String phoneNumberRestaurant = preferences.getString("PhoneNumberRestaurant", "defaultValue");
        String city = preferences.getString("City", "defaultValue");
        String address = preferences.getString("AddressRestaurant", "defaultValue");


        RestaurantFirstName.setText(firstNameOwner);
        RestaurantLastName.setText(lastNameOwner);
        RestaurantMail.setText(email);
        RestaurantPhone.setText(phoneNumberRestaurant);
        RestaurantAdress.setText(address);
        Restaurant.setText(restaurantName);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestaurantProfile.this,RestaurantEditProfile.class));


            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantProfile.this, MainActivityLogin.class);
                startActivity(intent);
                finishAffinity();
                SharedPreferences.Editor editorToDelete = preferences.edit();
                editorToDelete.clear();
                editorToDelete.commit();
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigationRestProfile);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        startActivity(new Intent(RestaurantProfile.this, RestaurantOpeningActivity.class));
                        return true;
                    case R.id.navigation_orders:
                        startActivity(new Intent(RestaurantProfile.this, RestaurantOrdersViewEditActivity.class));
                        return true;
                    case R.id.navigation_profile:
                        startActivity(new Intent(RestaurantProfile.this, RestaurantProfile.class));
                        return true;
                }
                return false;
            }
        });
    }
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Retrieve updated preferences
        String email = preferences.getString("RestaurantEmail", "defaultValue");
        String restaurantId = preferences.getString("RestaurantID", "defaultValue");
        String password = preferences.getString("PasswordOfRestaurant", "defaultValue");
        String restaurantName = preferences.getString("RestaurantName", "defaultValue");
        String firstNameOwner = preferences.getString("FirstNameOwner", "defaultValue");
        String lastNameOwner = preferences.getString("LastNameOwner", "defaultValue");
        String phoneNumberRestaurant = preferences.getString("PhoneNumberRestaurant", "defaultValue");
        String city = preferences.getString("City", "defaultValue");
        String address = preferences.getString("AddressRestaurant", "defaultValue");

        // Update text views
        RestaurantFirstName.setText(firstNameOwner);
        RestaurantLastName.setText(lastNameOwner);
        RestaurantMail.setText(email);
        RestaurantPhone.setText(phoneNumberRestaurant);
        RestaurantAdress.setText(address);
        Restaurant.setText(restaurantName);
    }
}