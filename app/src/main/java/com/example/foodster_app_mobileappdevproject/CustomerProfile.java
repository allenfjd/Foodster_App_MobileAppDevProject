package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomerProfile extends AppCompatActivity {
    TextView CustomerFirstName;
    TextView CustomerLastName;
    TextView CustomerMail;
    TextView CustomerPhone;
    TextView CustomerAdress;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        TextView CustomerFirstName=findViewById(R.id.txtCName);
        TextView CustomerLastName=findViewById(R.id.txtCLName);
        TextView CustomerMail=findViewById(R.id.txtCMail);
        TextView CustomerPhone=findViewById(R.id.txtCNumber);
        TextView CustomerAdress=findViewById(R.id.txtCAdress);
        Button btnEditProfile=findViewById(R.id.btnCEditProfile);
        Button btnLogout=findViewById(R.id.btnCLogout);

//        SharedPreferences preferences =
//                PreferenceManager.getDefaultSharedPreferences(this);
//        String email = preferences.getString("RestaurantEmail", "defaultValue");
//        String restaurantId = preferences.getString("RestaurantID", "defaultValue");
//        String password = preferences.getString("PasswordOfRestaurant", "defaultValue");
//        String restaurantName = preferences.getString("RestaurantName", "defaultValue");
//        String firstNameOwner = preferences.getString("FirstNameOwner", "defaultValue");
//        String lastNameOwner = preferences.getString("LastNameOwner", "defaultValue");
//        String phoneNumberRestaurant = preferences.getString("PhoneNumberRestaurant", "defaultValue");
//        String city = preferences.getString("City", "defaultValue");
//        String address = preferences.getString("AddressRestaurant", "defaultValue");

//        CustomerFirstName.setText(firstNameOwner);
//        CustomerLastName.setText(lastNameOwner);
//        CustomerMail.setText(email);
//        CustomerPhone.setText(phoneNumberRestaurant);
//        CustomerAdress.setText(address);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(CustomerProfile.this,CustomerEditProfile.class));

                }
            });

            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CustomerProfile.this, MainActivityLogin.class);
                    startActivity(intent);
                    finish();
                }
            });
    }
    @Override
    protected void onResume() {
        super.onResume();
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//
//        // Retrieve updated preferences
//        String email = preferences.getString("RestaurantEmail", "defaultValue");
//        String restaurantId = preferences.getString("RestaurantID", "defaultValue");
//        String password = preferences.getString("PasswordOfRestaurant", "defaultValue");
//        String restaurantName = preferences.getString("RestaurantName", "defaultValue");
//        String firstNameOwner = preferences.getString("FirstNameOwner", "defaultValue");
//        String lastNameOwner = preferences.getString("LastNameOwner", "defaultValue");
//        String phoneNumberRestaurant = preferences.getString("PhoneNumberRestaurant", "defaultValue");
//        String city = preferences.getString("City", "defaultValue");
//        String address = preferences.getString("AddressRestaurant", "defaultValue");

        // Update text views
//        CustomerFirstName.setText(firstNameOwner);
//        CustomerLastName.setText(lastNameOwner);
//        CustomerMail.setText(email);
//        CustomerPhone.setText(phoneNumberRestaurant);
//        CustomerAdress.setText(address);
    }
}
