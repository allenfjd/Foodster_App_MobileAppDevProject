package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerDeliveryOrder extends AppCompatActivity {

    DataBaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_delivery_order);

        TextView txtRestaurant = findViewById(R.id.txtRestaurantAdd);
        TextView txtCustomer = findViewById(R.id.txtCustomerAddress);
        TextView txtOrders = findViewById(R.id.txtOrder);
        TextView totalPrice = findViewById(R.id.txtFinalPrice);
        Button finalOrderBtn = findViewById(R.id.finalOrderBtn);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String customerEmail = preferences.getString("CustomerEmail","DefaultValue");
        dbh =new DataBaseHelper(this);
        Cursor c = dbh.viewDataFromCustomerTable(customerEmail);
        String customerAddress = "";
        if(c.getCount()>0){
            while(c.moveToNext()){
                customerAddress = c.getString(4);
            }
        }

        Bundle extra = getIntent().getExtras();
        String address = "";
        String order = "";
        String price="";
        if (extra != null) {
            address = extra.getString("address");
            order = extra.getString("order");
            price = extra.getString("price");
        }
        txtRestaurant.setText(address);
        txtOrders.setText(order);
        totalPrice.setText(price);

        txtCustomer.setText(customerAddress);

        finalOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Order created", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CustomerDeliveryOrder.this, CustomerOpeningActivity.class));
            }
        });
    }
}