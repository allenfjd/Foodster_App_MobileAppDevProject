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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RestaurantEditProfile extends AppCompatActivity {
    DataBaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_edit_profile);
        dbh = new DataBaseHelper( this);
        EditText RestaurantFirstName=findViewById(R.id.RPersonNameEditProfile);
        EditText RestaurantLastName=findViewById(R.id.etxtRLastName);
        EditText RestaurantMail=findViewById(R.id.etxtREmailEditProfile);
        EditText RestaurantPhone=findViewById(R.id.etxtPhoneEditProfile);
        EditText RestaurantAdress=findViewById(R.id.etxtRAdressEditProfile);
        EditText Restaurant=findViewById(R.id.etxtRestaurantEditProfile);
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String firstNameOwner = preferences.getString("FirstNameOwner", "");
        String lastNameOwner = preferences.getString("LastNameOwner", "");
        String phoneNumberRestaurant = preferences.getString("PhoneNumberRestaurant", "");
        String address = preferences.getString("AddressRestaurant", "");
        String email = preferences.getString("RestaurantEmail", "defaultValue");
        String restaurantName = preferences.getString("RestaurantName", "defaultValue");


        RestaurantFirstName.setText(firstNameOwner);
        RestaurantLastName.setText(lastNameOwner);
        RestaurantPhone.setText(phoneNumberRestaurant);
        RestaurantAdress.setText(address);
        RestaurantMail.setText(email);
        Restaurant.setText(restaurantName);
        Button buttonSaveChanges = findViewById(R.id.btnRSaveChanges);
        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newFirstName = RestaurantFirstName.getText().toString();
                String newLastName = RestaurantLastName.getText().toString();
                String newPhone = RestaurantPhone.getText().toString();
                String newAddress = RestaurantAdress.getText().toString();
                String newRestaurantName = Restaurant.getText().toString();

                System.out.println(newFirstName+newLastName+newAddress+newPhone+newRestaurantName);
                dbh.updateInfo(email,newFirstName,newLastName,newPhone, newAddress ,newRestaurantName);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("FirstNameOwner", newFirstName);
                editor.putString("LastNameOwner", newLastName);
                editor.putString("PhoneNumberRestaurant", newPhone);
                editor.putString("AddressRestaurant", newAddress);
                editor.putString("RestaurantName", newRestaurantName);

                editor.apply();


                Toast.makeText(RestaurantEditProfile.this, "Related field is updated", Toast.LENGTH_LONG).show();

                finish();
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigationRestEdProfile);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        startActivity(new Intent(RestaurantEditProfile.this, RestaurantOpeningActivity.class));
                        return true;
                    case R.id.navigation_orders:
                        startActivity(new Intent(RestaurantEditProfile.this, RestaurantOrdersViewEditActivity.class));
                        return true;
                    case R.id.navigation_profile:
                        startActivity(new Intent(RestaurantEditProfile.this, RestaurantProfile.class));
                        return true;
                }
                return false;
            }
        });

    }
}