package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerPickupOrder extends AppCompatActivity {

    DataBaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_pickup_order);

        TextView txtRestaurant = findViewById(R.id.txtRestaurantAdd);
        TextView txtPickup = findViewById(R.id.txtTime);
        TextView txtOrders = findViewById(R.id.txtOrder);
        TextView totalPrice = findViewById(R.id.txtFinalPrice);
        Button finalOrderBtn = findViewById(R.id.finalOrderBtn);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = formatter.format(new Date());


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String customerEmail = preferences.getString("CustomerEmail","DefaultValue");
        dbh =new DataBaseHelper(this);

        Bundle extras = getIntent().getExtras();
        String address = "";
        String pickupTime = "";
        String orders = "";
        String price="";
        String id = "";
        if (extras != null) {
            address = extras.getString("address");
            pickupTime = extras.getString("pickup");
            orders = extras.getString("order");
            price = extras.getString("price");
            id = extras.getString("Id");
        }
        String formattedPrice ="";
        if (price.matches("")) {
            formattedPrice ="no orders";
        }else{
            formattedPrice = String.format("%.2f", Double.valueOf(price));
        }
        txtRestaurant.setText(address);
        txtPickup.setText(pickupTime);
        txtOrders.setText(orders);
        totalPrice.setText("Total price: $" + formattedPrice);

        String finalId = id;
        String pickup = "Pickup";
        String finalOrders = orders;
        finalOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] theOrder = finalOrders.split("\n");
                String result = "";
                for (int i=0;i < theOrder.length; i++){
                    String line = theOrder[i].trim();
                    int spaceIndex = line.indexOf(" ");
                    if(spaceIndex != -1){
                        String firstWord = line.substring(0, spaceIndex);
                        result += firstWord;
                        if(i< theOrder.length -1){
                            result += "|";
                        }
                    }
                }

                dbh.addDataOrderTable(customerEmail, finalId, currentDate, result, String.valueOf(1), pickup, "In Progress", 0);
                Toast.makeText(getApplicationContext(), "Order created", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CustomerPickupOrder.this, CustomerOpeningActivity.class));
            }
        });


    }
}