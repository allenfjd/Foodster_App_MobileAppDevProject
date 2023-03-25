package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomerOpeningActivity extends AppCompatActivity {

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
