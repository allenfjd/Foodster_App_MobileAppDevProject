package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class CustomerDeliveryOrder extends AppCompatActivity {

    DataBaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_delivery_order);

        TextView txtRestaurant = findViewById(R.id.txtRestaurantAddress);
        TextView txtCustomer = findViewById(R.id.txtCustomerAddress);

        Bundle extras = getIntent().getExtras();
        String address = "";
        if (extras != null) {
            address = extras.getString("address");
        }
        txtRestaurant.setText(address);

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

        txtCustomer.setText(customerAddress);
    }
}