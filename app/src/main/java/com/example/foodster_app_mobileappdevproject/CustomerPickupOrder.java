package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CustomerPickupOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_pickup_order);

        TextView txtRestaurant = findViewById(R.id.txtRestaurantAddress);
        TextView txtPickup = findViewById(R.id.txtTime);

        Bundle extras = getIntent().getExtras();
        String address = "";
        String pickupTime = "";
        if (extras != null) {
            address = extras.getString("address");
            pickupTime = extras.getString("pickup");
        }
        txtRestaurant.setText(address);
        txtPickup.setText(pickupTime);
    }
}