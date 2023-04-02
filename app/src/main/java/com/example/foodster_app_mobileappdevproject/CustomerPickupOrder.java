package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerPickupOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_pickup_order);

        TextView txtRestaurant = findViewById(R.id.txtRestaurantAdd);
        TextView txtPickup = findViewById(R.id.txtTime);
        TextView txtOrders = findViewById(R.id.txtOrder);
        TextView totalPrice = findViewById(R.id.txtFinalPrice);
        Button finalOrderBtn = findViewById(R.id.finalOrderBtn);

        Bundle extras = getIntent().getExtras();
        String address = "";
        String pickupTime = "";
        String orders = "";
        String price="";
        if (extras != null) {
            address = extras.getString("address");
            pickupTime = extras.getString("pickup");
            orders = extras.getString("order");
            price = extras.getString("price");
        }
        txtRestaurant.setText(address);
        txtPickup.setText(pickupTime);
        txtOrders.setText(orders);
        totalPrice.setText(price);

        finalOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Order created", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CustomerPickupOrder.this, CustomerOpeningActivity.class));
            }
        });


    }
}