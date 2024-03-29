package com.example.foodster_app_mobileappdevproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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
        CustomerFirstName=findViewById(R.id.txtCName);
        CustomerLastName=findViewById(R.id.txtCLName);
        CustomerMail=findViewById(R.id.txtCMail);
        CustomerPhone=findViewById(R.id.txtCNumber);
        CustomerAdress=findViewById(R.id.txtCAdress);
        Button btnEditProfile=findViewById(R.id.btnCEditProfile);
        Button btnLogout=findViewById(R.id.btnCLogout);

        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String email = preferences.getString("CustomerEmail", "defaultValue");
        String firstNameCustomer = preferences.getString("FirstNameCustomer", "defaultValue");
        String lastNameCustomer = preferences.getString("LastNameCustomer", "defaultValue");
        String phoneNumberCustomer = preferences.getString("PhoneNumberCustomer", "defaultValue");
        String addressCustomer = preferences.getString("AddressCustomer", "defaultValue");

        CustomerFirstName.setText(firstNameCustomer);
        CustomerLastName.setText(lastNameCustomer);
        CustomerMail.setText(email);
        CustomerPhone.setText(phoneNumberCustomer);
        CustomerAdress.setText(addressCustomer);

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
                    finishAffinity();
                    SharedPreferences.Editor editorToDelete = preferences.edit();
                    editorToDelete.clear();
                    editorToDelete.commit();
                }
            });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        startActivity(new Intent(CustomerProfile.this, CustomerOpeningActivity.class));
                        return true;
                    case R.id.navigation_orders:
                        startActivity(new Intent(CustomerProfile.this, CustomerOrders.class));
                        return true;
                    case R.id.navigation_profile:
                        return true;
                }
                return false;
            }

        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Retrieve updated preferences
        String email = preferences.getString("CustomerEmail", "defaultValue");
        String firstNameCustomer = preferences.getString("FirstNameCustomer", "defaultValue");
        String lastNameCustomer = preferences.getString("LastNameCustomer", "defaultValue");
        String phoneNumberCustomer = preferences.getString("PhoneNumberCustomer", "defaultValue");
        String addressCustomer = preferences.getString("AddressCustomer", "defaultValue");

        //Update text views
        CustomerFirstName.setText(firstNameCustomer);
        CustomerLastName.setText(lastNameCustomer);
        CustomerMail.setText(email);
        CustomerPhone.setText(phoneNumberCustomer);
        CustomerAdress.setText(addressCustomer);
    }


}
