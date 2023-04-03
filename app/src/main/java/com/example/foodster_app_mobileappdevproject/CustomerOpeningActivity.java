package com.example.foodster_app_mobileappdevproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerOpeningActivity extends AppCompatActivity {

    DataBaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_opening);
        Button btnNewWest = findViewById(R.id.newWestBtn);
        Button btnEastVan = findViewById(R.id.eastVanBtn);
        Button btnNorthVan = findViewById(R.id.northVantbtn);
        Button btnSouthVan = findViewById(R.id.southVanBtn);
        Button btnWestVan = findViewById(R.id.westVanBtn);
        Button btnRichmond = findViewById(R.id.richmondBtn);
        Button btnBurnaby = findViewById(R.id.burnabyBtn);
        Button btnSurrey = findViewById(R.id.surreyBtn);
        TextView customerName = findViewById(R.id.textView4);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String customerEmail = preferences.getString("CustomerEmail","DefaultValue");
        dbh =new DataBaseHelper(this);
        Cursor c = dbh.viewDataFromCustomerTable(customerEmail);
        String firstName = "";
        String lastName ="";
        if(c.getCount()>0){
            while(c.moveToNext()){
                firstName = c.getString(2);
                lastName = c .getString(3);
            }
        }
        customerName.setText("Welcome " + firstName + " " + lastName);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:

                        return true;
                    case R.id.navigation_orders:
                        startActivity(new Intent(CustomerOpeningActivity.this, CustomerOrders.class));
                        return true;
                    case R.id.navigation_profile:
                        startActivity(new Intent(CustomerOpeningActivity.this, CustomerProfile.class));
                        return true;
                }
                return false;
            }

        });

        btnNewWest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerOpeningActivity.this, CustomerRestaurantsNewWest.class));
            }
        });

        btnEastVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerOpeningActivity.this, CustomerRestaurantsEastVan.class));
            }
        });
        btnNorthVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerOpeningActivity.this, CustomerRestaurantsNorthVan.class));
            }
        });
        btnSouthVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerOpeningActivity.this, CustomerRestaurantsSouthVan.class));
            }
        });
        btnWestVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerOpeningActivity.this, CustomerRestaurantsWestVan.class));
            }
        });
        btnRichmond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerOpeningActivity.this, CustomerRestaurantsRichmond.class));
            }
        });
        btnBurnaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerOpeningActivity.this, CustomerRestaurantsBurnaby.class));
            }
        });
        btnSurrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerOpeningActivity.this, CustomerRestaurantsSurrey.class));
            }
        });
        }
    }
